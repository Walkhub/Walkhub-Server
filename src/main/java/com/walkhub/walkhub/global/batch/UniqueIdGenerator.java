package com.walkhub.walkhub.global.batch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UniqueIdGenerator extends RunIdIncrementer {
    private static final String RUN_ID = "run.id";

    @Override
    public JobParameters getNext(JobParameters parameters) {
        return new JobParametersBuilder()
                .addDate(RUN_ID, new Date())
                .toJobParameters();
    }

}