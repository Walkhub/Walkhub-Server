package com.walkhub.walkhub.domain.rank.job;

import com.walkhub.walkhub.domain.rank.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.StoredProcedureItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.SqlParameter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Types;
import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class UserRankJob {

    private static final Integer CHUNK_SIZE = 100;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory em;
    private final DataSource dataSource;

    @Bean
    public Job userJob() {
        return jobBuilderFactory.get("userRankJob")
                .start(dailyUserRankStep(null))
                .next(weeklyUserRankStep(null))
                .next(monthlyUserRankStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step dailyUserRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("dailyUserRankStep")
                .<UserRankBySchool, UserRank>chunk(CHUNK_SIZE)
                .reader(dailyUserRankReader(null))
                .processor(dailyUserRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step weeklyUserRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("weeklyUserRankStep")
                .<UserRankBySchool, UserRank>chunk(CHUNK_SIZE)
                .reader(weeklyUserRankReader(null))
                .processor(weeklyUserRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step monthlyUserRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("monthlyUserRankStep")
                .<UserRankBySchool, UserRank>chunk(CHUNK_SIZE)
                .reader(monthlyUserRankReader(null))
                .processor(monthlyUserRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }
    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankBySchool> dailyUserRankReader(@Value("#{jobParameters[stepKey]}") String jobKey) {
        return buildParameters("dailyUserRankReader", 1);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankBySchool> weeklyUserRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return buildParameters("weeklyUserRankReader", 7);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankBySchool> monthlyUserRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return buildParameters("monthlyUserRankReader", 31);
    }

    @Bean
    @StepScope
    public ItemProcessor<UserRankBySchool, UserRank> dailyUserRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                    .accountId(rankInfo.getAccountId())
                    .createdAt(LocalDate.now())
                    .dateType("DAY")
                    .agencyCode(rankInfo.getAgencyCode())
                    .name(rankInfo.getName())
                    .grade(rankInfo.getGrade())
                    .classNum(rankInfo.getClassNum())
                    .profileImageUrl(rankInfo.getProfileImageUrl())
                    .ranking(rankInfo.getRanking())
                    .walkCount(rankInfo.getWalkCount())
                    .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<UserRankBySchool, UserRank> weeklyUserRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .accountId(rankInfo.getAccountId())
                .createdAt(LocalDate.now())
                .dateType("WEEK")
                .agencyCode(rankInfo.getAgencyCode())
                .name(rankInfo.getName())
                .grade(rankInfo.getGrade())
                .classNum(rankInfo.getClassNum())
                .profileImageUrl(rankInfo.getProfileImageUrl())
                .ranking(rankInfo.getRanking())
                .walkCount(rankInfo.getWalkCount())
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<UserRankBySchool, UserRank> monthlyUserRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .accountId(rankInfo.getAccountId())
                .createdAt(LocalDate.now())
                .dateType("MONTH")
                .agencyCode(rankInfo.getAgencyCode())
                .name(rankInfo.getName())
                .grade(rankInfo.getGrade())
                .classNum(rankInfo.getClassNum())
                .profileImageUrl(rankInfo.getProfileImageUrl())
                .ranking(rankInfo.getRanking())
                .walkCount(rankInfo.getWalkCount())
                .build();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<UserRank> userRankWriter(@Value("#{jobParameters[jobKey]}") String jobKey) {
        JdbcBatchItemWriter<UserRank> writer = new JdbcBatchItemWriterBuilder<UserRank>()
                .dataSource(dataSource)
                .sql("CALL SAVE_USER_RANK(:accountId, :createdAt, :dateType, :agencyCode, :name, :grade, :classNum, :profileImageUrl, :ranking, :walkCount)")
                .beanMapped()
                .build();

        writer.afterPropertiesSet();
        return writer;
    }

    private StoredProcedureItemReader<UserRankBySchool> buildParameters(String builderName, Integer dateType) {
        return new StoredProcedureItemReaderBuilder<UserRankBySchool>()
                .name(builderName)
                .fetchSize(CHUNK_SIZE)
                .dataSource(dataSource)
                .procedureName("SELECT_USER_RANK_BY_SCHOOL_AND_DATETYPE")
                .parameters(new SqlParameter[]{new SqlParameter("date_type_in", Types.INTEGER)})
                .preparedStatementSetter(new ArgumentPreparedStatementSetter(new Object[]{dateType}))
                .rowMapper((rs, rowNum) -> UserRankBySchool.builder()
                        .name(rs.getString("name"))
                        .agencyCode(rs.getString("agency_code"))
                        .grade(rs.getInt("grade"))
                        .classNum(rs.getInt("class_num"))
                        .profileImageUrl(rs.getString("profile_image_url"))
                        .accountId(rs.getString("account_id"))
                        .walkCount(rs.getInt("walk_count"))
                        .ranking(rs.getInt("ranking"))
                        .build())
                .build();
    }
}
