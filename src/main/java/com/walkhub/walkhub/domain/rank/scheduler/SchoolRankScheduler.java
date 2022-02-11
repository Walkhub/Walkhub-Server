package com.walkhub.walkhub.domain.rank.scheduler;

import com.walkhub.walkhub.domain.rank.job.SchoolRankJob;
import com.walkhub.walkhub.global.batch.UniqueIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SchoolRankScheduler {
    private final SchoolRankJob schoolRankJob;
    private final JobLauncher jobLauncher;
    private final UniqueIdGenerator uniqueIdGenerator;

//    @Scheduled(cron = "0 0 * * 1 *", zone = "Asia/Seoul") // 일주일(매주 월요일)마다로 변경
    @Scheduled(cron = "*/10 * * * * *") // 10초 마다 실행
    public void saveSchoolRank() throws
            JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {
        jobLauncher.run(schoolRankJob.schoolJob(), uniqueIdGenerator.getNext(null));
    }

}
