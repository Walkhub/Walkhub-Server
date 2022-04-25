package com.walkhub.walkhub.domain.rank.job;

import com.walkhub.walkhub.domain.rank.domain.repository.vo.SchoolRankWriterVO;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
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
public class SchoolRankJob {

    private final DataSource dataSource;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job schoolJob() {
        return jobBuilderFactory.get(SCHOOL_RANK_JOB)
                .start(querySchoolRankForLastWeekStep(null))
                .next(querySchoolRankForLastMonthStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step querySchoolRankForLastWeekStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get(WEEK_SCHOOL_RANK_STEP)
                .<SchoolRankInfo, SchoolRankWriterVO>chunk(CHUNK_SIZE)
                .reader(schoolRankForLastWeekReader(null))
                .processor(schoolRankForLastWeekProcessor(null))
                .writer(schoolRankWriter(null))
                .build();
    }

    @Bean
    @JobScope
    public Step querySchoolRankForLastMonthStep(@Value("#{jobParameters[jobkey]}") String jobKey) {
        return stepBuilderFactory.get(MONTH_SCHOOL_RANK_STEP)
                .<SchoolRankInfo, SchoolRankWriterVO>chunk(CHUNK_SIZE)
                .reader(schoolRankForLastMonthReader(null))
                .processor(schoolRankForLastMonthProcessor(null))
                .writer(schoolRankWriter(null))
                .build();
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<SchoolRankInfo> schoolRankForLastWeekReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure(WEEK_SCHOOL_RANK_READER, DATE_WEEK);
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<SchoolRankInfo> schoolRankForLastMonthReader(@Value("#{jobParameters[stepKey]}") Integer type) {
        return callProcedure(MONTH_SCHOOL_RANK_READER, DATE_MONTH);
    }

    @Bean
    @StepScope
    public ItemProcessor<SchoolRankInfo, SchoolRankWriterVO> schoolRankForLastWeekProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> SchoolRankWriterVO.builder()
                .schoolId(rankInfo.getSchoolId())
                .createdAt(LocalDate.now())
                .dateType(SchoolDateType.WEEK)
                .name(rankInfo.getName())
                .logoImageUrl(rankInfo.getLogoImageUrl())
                .userCount(rankInfo.getUserCount())
                .walkCount(rankInfo.getWalkCount())
                .ranking(rankInfo.getRanking())
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<SchoolRankInfo, SchoolRankWriterVO> schoolRankForLastMonthProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> SchoolRankWriterVO.builder()
                .schoolId(rankInfo.getSchoolId())
                .createdAt(LocalDate.now())
                .dateType(SchoolDateType.MONTH)
                .name(rankInfo.getName())
                .logoImageUrl(rankInfo.getLogoImageUrl())
                .userCount(rankInfo.getUserCount())
                .walkCount(rankInfo.getWalkCount())
                .ranking(rankInfo.getRanking())
                .build();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<SchoolRankWriterVO> schoolRankWriter(@Value("#{jobParameters[jobKey]}") String jobKey) {
        JdbcBatchItemWriter<SchoolRankWriterVO> writer = new JdbcBatchItemWriterBuilder<SchoolRankWriterVO>()
                .dataSource(dataSource)
                .sql(SAVE_SCHOOL_RANK_PROCEDURE)
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .beanMapped()
                .build();

        writer.afterPropertiesSet();
        return writer;
    }

    private StoredProcedureItemReader<SchoolRankInfo> callProcedure(String builderName, String dateType) {
        return new StoredProcedureItemReaderBuilder<SchoolRankInfo>()
                .name(builderName)
                .fetchSize(CHUNK_SIZE)
                .dataSource(dataSource)
                .procedureName(SELECT_SCHOOL_RANK_PROCEDURE)
                .parameters(new SqlParameter("_DATETYPE", Types.VARCHAR))
                .preparedStatementSetter(new ArgumentPreparedStatementSetter(new Object[]{dateType}))
                .rowMapper((rs, rowNum) -> SchoolRankInfo.builder()
                        .schoolId(rs.getLong("school_id"))
                        .name(rs.getString("name"))
                        .logoImageUrl(rs.getString("logo_image_url"))
                        .userCount(rs.getLong("user_count"))
                        .walkCount(rs.getInt("walk_count"))
                        .ranking(rs.getInt("ranking"))
                        .build())
                .build();
    }

}
