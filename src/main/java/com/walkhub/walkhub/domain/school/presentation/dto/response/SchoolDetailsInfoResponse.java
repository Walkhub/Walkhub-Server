package com.walkhub.walkhub.domain.school.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SchoolDetailsInfoResponse {

    private final DateRankResponse week;
    private final DateRankResponse month;

    @Getter
    @Builder
    public static class DateRankResponse {
        private final Long userCount;
        private final LocalDate date;
        private final Integer totalWalkCount;
        private final Integer ranking;
    }

}
