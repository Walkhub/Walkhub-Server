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
                .start(schoolRankStep("schoolRankJob"))
                .build();
    }

    @Bean
    @JobScope
    public Step schoolRankStep(@Value("#{jobParameters[jobKey]}") String jobKey) {
        return stepBuilderFactory.get("SchoolRankStep")
                .<SchoolRankInfo, SchoolRank>chunk(CHUNK_SIZE)
                .reader(schoolReader("schoolRankStep"))
                .processor(schoolRankProcessor())
                .writer(schoolRankWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaCursorItemReader<SchoolRankInfo> schoolReader(@Value("#{jobParameters[stepKey]}") String stepKey) {
        return new JpaCursorItemReaderBuilder<SchoolRankInfo>()
                .name("SchoolRankReader")
                .entityManagerFactory(em)
                .currentItemCount(CHUNK_SIZE)
                .queryString("SELECT sr FROM SchoolRankInfo sr")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<SchoolRankInfo, SchoolRank> schoolRankProcessor() {
        return rankDto -> SchoolRank.builder()
                .agencyCode(rankDto.getAgencyCode())
                .name(rankDto.getName())
                .walkCount(rankDto.getWalkCount())
                .logoImageUrl(rankDto.getLogoImageUrl())
                .ranking(rankDto.getRanking())
                .build();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<SchoolRank> schoolRankWriter() {
        JdbcBatchItemWriter<SchoolRank> writer = new JdbcBatchItemWriterBuilder<SchoolRank>()
                .dataSource(dataSource)
                .sql("INSERT INTO SchoolRank VALUES (:rank, :agencyCode, :name, :logoImageUrl, :walkCount)")
                .beanMapped()
                .build();

        writer.afterPropertiesSet();
        return writer;
    }

}
