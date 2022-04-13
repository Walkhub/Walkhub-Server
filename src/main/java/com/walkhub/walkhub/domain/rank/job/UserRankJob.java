package com.walkhub.walkhub.domain.rank.job;

import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankWriterVO;
import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.StoredProcedureItemReader;
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
        return stepBuilderFactory.get(WEEK_USER_SCHOOL_RANK_STEP)
                .<UserRankInfo, UserRankWriterVO>chunk(CHUNK_SIZE)
                .reader(weekUserSchoolRankReader(null))
                .processor(weekUserSchoolRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step monthUserSchoolRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get(MONTH_USER_SCHOOL_RANK_STEP)
                .<UserRankInfo, UserRankWriterVO>chunk(CHUNK_SIZE)
                .reader(monthUserSchoolRankReader(null))
                .processor(monthUserSchoolRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step weekUserClassRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get(WEEK_USER_CLASS_RANK_STEP)
                .<UserRankInfo, UserRankWriterVO>chunk(CHUNK_SIZE)
                .reader(weekUserClassRankReader(null))
                .processor(weekUserClassRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step monthUserClassRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get(MONTH_USER_CLASS_RANK_STEP)
                .<UserRankInfo, UserRankWriterVO>chunk(CHUNK_SIZE)
                .reader(monthUserClassRankReader(null))
                .processor(monthUserClassRankProcessor(null))
                .writer(userRankWriter(null))
                .build();
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> weekUserSchoolRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure(WEEK_USER_SCHOOL_RANK_READER, SELECT_USER_RANK_BY_SCHOOL_PROCEDURE, 7);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> monthUserSchoolRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure(MONTH_USER_SCHOOL_RANK_READER, SELECT_USER_RANK_BY_SCHOOL_PROCEDURE, 28);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> weekUserClassRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure(WEEK_USER_CLASS_RANK_READER, SELECT_USER_RANK_BY_CLASS_PROCEDURE, 7);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<UserRankInfo> monthUserClassRankReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure(MONTH_USER_CLASS_RANK_READER, SELECT_USER_RANK_BY_CLASS_PROCEDURE, 28);
    }

    @Bean
    @StepScope
    public ItemProcessor<UserRankInfo, UserRankWriterVO> weekUserSchoolRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRankWriterVO.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType(DATE_WEEK)
                .scopeType(USER_SCOPE_SCHOOL)
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
    public ItemProcessor<UserRankInfo, UserRankWriterVO> monthUserSchoolRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRankWriterVO.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType(DATE_MONTH)
                .scopeType(USER_SCOPE_SCHOOL)
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
    public ItemProcessor<UserRankInfo, UserRankWriterVO> weekUserClassRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRankWriterVO.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType(DATE_WEEK)
                .scopeType(USER_SCOPE_CLASS)
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
    public ItemProcessor<UserRankInfo, UserRankWriterVO> monthUserClassRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> UserRankWriterVO.builder()
                .userId(rankInfo.getUserId())
                .createdAt(LocalDate.now())
                .dateType(DATE_WEEK)
                .scopeType(USER_SCOPE_CLASS)
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
    public JdbcBatchItemWriter<UserRankWriterVO> userRankWriter(@Value("#{jobParameters[jobKey]}") String jobKey) {
        JdbcBatchItemWriter<UserRankWriterVO> writer = new JdbcBatchItemWriterBuilder<UserRankWriterVO>()
                .dataSource(dataSource)
                .sql(SAVE_USER_RANK_PROCEDURE)
                .beanMapped()
                .build();

        writer.afterPropertiesSet();
        return writer;
    }

    private StoredProcedureItemReader<UserRankInfo> callProcedure(String builderName, String procedureName,
                                                                  Integer dateType) {
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
