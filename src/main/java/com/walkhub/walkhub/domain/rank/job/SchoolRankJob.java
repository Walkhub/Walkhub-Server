package com.walkhub.walkhub.domain.rank.job;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.SchoolRankInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SchoolRankJob {

    private static final Integer CHUNK_SIZE = 100;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory em;
    private final DataSource dataSource;

    @Bean
    public Job rankJob() {
        return jobBuilderFactory.get("SchoolRankJob")
                .start(schoolRankStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step schoolRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("SchoolRankStep")
                .<SchoolRankInfo, SchoolRank>chunk(CHUNK_SIZE)
                .reader(schoolReader(null))
                .processor(schoolRankProcessor(null))
                .writer(schoolRankWriter(null))
                .build();
    }

    @Bean
    @StepScope
    public JpaCursorItemReader<SchoolRankInfo> schoolReader(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return new JpaCursorItemReaderBuilder<SchoolRankInfo>()
                .name("SchoolRankReader")
                .entityManagerFactory(em)
                .currentItemCount(CHUNK_SIZE)
                .queryString("SELECT sr FROM SchoolRankInfo sr")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<SchoolRankInfo, SchoolRank> schoolRankProcessor(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return rankInfo -> SchoolRank.builder()
                .agencyCode(rankInfo.getAgencyCode())
                .name(rankInfo.getName())
                .walkCount(rankInfo.getWalkCount())
                .logoImageUrl(rankInfo.getLogoImageUrl())
                .ranking(rankInfo.getRanking())
                .build();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<SchoolRank> schoolRankWriter(@Value("#{jobParameters[jobKey]}") String jobKey) {
        JdbcBatchItemWriter<SchoolRank> writer = new JdbcBatchItemWriterBuilder<SchoolRank>()
                .dataSource(dataSource)
                .sql("INSERT INTO SchoolRank VALUES (:agencyCode, :name, :ranking, :logoImageUrl, :walkCount)")
                .beanMapped()
                .build();

        writer.afterPropertiesSet();
        return writer;
    }

}
