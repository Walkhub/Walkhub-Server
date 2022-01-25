package com.walkhub.walkhub.global.batch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UniqueIdGenerator extends RunIdIncrementer {
    private static final String RUN_ID = "run.id";
    private static final Long DEFAULT_VALUE = 0L;

    @Override
    public JobParameters getNext(@Nullable JobParameters parameters) {
        JobParameters params = parameters == null ? new JobParameters() : parameters;
        return new JobParametersBuilder()
            .addLong(RUN_ID, params.getLong(RUN_ID, DEFAULT_VALUE) + 1)
            .toJobParameters();
    }

}