package com.walkhub.walkhub.domain.rank.job;

import com.walkhub.walkhub.domain.rank.domain.*;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankInfo;
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
                .start(weeklyUserSchoolRankStep(null))
                .next(monthlyUserSchoolRankStep(null))
                .next(weeklyUserClassRankStep(null))
                .next(monthlyUserClassRankStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step weeklyUserSchoolRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("weeklyUserSchoolRankStep")
                .<UserRankInfo, UserRank>chunk(CHUNK_SIZE)
                .reader(weeklyUserSchoolRankReader(null))
                .processor(weeklyUserSchoolRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step monthlyUserSchoolRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("monthlyUserSchoolRankStep")
                .<UserRankInfo, UserRank>chunk(CHUNK_SIZE)
                .reader(monthlyUserSchoolRankReader(null))
                .processor(monthlyUserSchoolRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step weeklyUserClassRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("weeklyUserClassRankStep")
                .<UserRankInfo, UserRank>chunk(CHUNK_SIZE)
                .reader(weeklyUserClassRankReader(null))
                .processor(weeklyUserClassRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step monthlyUserClassRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("monthlyUserClassRankStep")
                .<UserRankInfo, UserRank>chunk(CHUNK_SIZE)
                .reader(monthlyUserClassRankReader(null))
                .processor(monthlyUserClassRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> weeklyUserSchoolRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure("weeklyUserSchoolRankReader", "SELECT_USER_RANK_BY_SCHOOL", 7);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> monthlyUserSchoolRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure("monthlyUserSchoolRankReader", "SELECT_USER_RANK_BY_SCHOOL", 28);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> weeklyUserClassRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure("weeklyUserClassRankReader", "SELECT_USER_RANK_BY_CLASS", 7);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> monthlyUserClassRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure("monthlyUserClassRankReader", "SELECT_USER_RANK_BY_CLASS", 28);
    }

    @Bean
    @StepScope
    public ItemProcessor<UserRankInfo, UserRank> weeklyUserSchoolRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType("WEEK")
                .scopeType("SCHOOL")
                .schoolId(rankInfo.getSchoolId())
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
    public ItemProcessor<UserRankInfo, UserRank> monthlyUserSchoolRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType("MONTH")
                .scopeType("SCHOOL")
                .schoolId(rankInfo.getSchoolId())
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
    public ItemProcessor<UserRankInfo, UserRank> weeklyUserClassRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType("WEEK")
                .scopeType("CLASS")
                .schoolId(rankInfo.getSchoolId())
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
    public ItemProcessor<UserRankInfo, UserRank> monthlyUserClassRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType("MONTH")
                .scopeType("CLASS")
                .schoolId(rankInfo.getSchoolId())
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
                .sql("CALL SAVE_USER_RANK(:userId, :createdAt, :dateType, :scopeType, :schoolId, :name, :grade, :classNum, :profileImageUrl, :ranking, :walkCount)")
                .beanMapped()
                .build();

        writer.afterPropertiesSet();
        return writer;
    }

    private StoredProcedureItemReader<UserRankInfo> callProcedure(String builderName, String procedureName, Integer dateType) {
        return new StoredProcedureItemReaderBuilder<UserRankInfo>()
                .name(builderName)
                .fetchSize(CHUNK_SIZE)
                .dataSource(dataSource)
                .procedureName(procedureName)
                .parameters(new SqlParameter("date_type_in", Types.INTEGER))
                .preparedStatementSetter(new ArgumentPreparedStatementSetter(new Object[]{dateType}))
                .rowMapper((rs, rowNum) -> UserRankInfo.builder()
                        .name(rs.getString("name"))
                        .schoolId(rs.getLong("school_id"))
                        .grade(rs.getInt("grade"))
                        .classNum(rs.getInt("class_num"))
                        .profileImageUrl(rs.getString("profile_image_url"))
                        .userId(rs.getLong("user_id"))
                        .walkCount(rs.getInt("walk_count"))
                        .ranking(rs.getInt("ranking"))
                        .build())
                .build();
    }
}
