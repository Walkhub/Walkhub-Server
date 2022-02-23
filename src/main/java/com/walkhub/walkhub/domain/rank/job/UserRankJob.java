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

import javax.sql.DataSource;
import java.sql.Types;
import java.time.LocalDate;

import static com.walkhub.walkhub.domain.rank.job.constant.RankJobConstant.*;

@Configuration
@RequiredArgsConstructor
public class UserRankJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job userJob() {
        return jobBuilderFactory.get(USER_RANK_JOB)
                .start(weekUserSchoolRankStep(null))
                .next(monthUserSchoolRankStep(null))
                .next(weekUserClassRankStep(null))
                .next(monthUserClassRankStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step weekUserSchoolRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("weekUserSchoolRankStep")
                .<UserRankInfo, UserRank>chunk(CHUNK_SIZE)
                .reader(weekUserSchoolRankReader(null))
                .processor(weekUserSchoolRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step monthUserSchoolRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("monthUserSchoolRankStep")
                .<UserRankInfo, UserRank>chunk(CHUNK_SIZE)
                .reader(monthUserSchoolRankReader(null))
                .processor(monthUserSchoolRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step weekUserClassRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("weeklyUserClassRankStep")
                .<UserRankInfo, UserRank>chunk(CHUNK_SIZE)
                .reader(weekUserClassRankReader(null))
                .processor(weekUserClassRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step monthUserClassRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("monthUserClassRankStep")
                .<UserRankInfo, UserRank>chunk(CHUNK_SIZE)
                .reader(monthUserClassRankReader(null))
                .processor(monthUserClassRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> weekUserSchoolRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure("weekUserSchoolRankReader", "SELECT_USER_RANK_BY_SCHOOL", 7);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> monthUserSchoolRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure("monthUserSchoolRankReader", "SELECT_USER_RANK_BY_SCHOOL", 28);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> weekUserClassRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure("weekUserClassRankReader", "SELECT_USER_RANK_BY_CLASS", 7);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> monthUserClassRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure("monthUserClassRankReader", "SELECT_USER_RANK_BY_CLASS", 28);
    }

    @Bean
    @StepScope
    public ItemProcessor<UserRankInfo, UserRank> weekUserSchoolRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType(DATE_WEEK)
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
    public ItemProcessor<UserRankInfo, UserRank> monthUserSchoolRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType(DATE_MONTH)
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
    public ItemProcessor<UserRankInfo, UserRank> weekUserClassRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType(DATE_WEEK)
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
    public ItemProcessor<UserRankInfo, UserRank> monthUserClassRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRank.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType(DATE_MONTH)
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
