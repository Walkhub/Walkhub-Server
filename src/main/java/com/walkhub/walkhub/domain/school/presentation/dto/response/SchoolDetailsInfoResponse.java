package com.walkhub.walkhub.domain.school.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SchoolDetailsInfoResponse {

    private final Long totalUserCount;
    private final Integer weekTotalWalkCount;
    private final Integer monthTotalWalkCount;
    private final Integer weekRanking;
    private final Integer monthRanking;

}
