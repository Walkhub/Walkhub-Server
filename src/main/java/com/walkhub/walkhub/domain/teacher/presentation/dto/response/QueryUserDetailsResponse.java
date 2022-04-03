package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryUserDetailsResponse {
    private final Long userId;
    private final String name;
    private final String profileImageUrl;
    private final Integer grade;
    private final Integer classNum;
    private final Integer number;
    private final List<Integer> walkCountList;
    private final Integer averageWalkCount;
    private final Integer totalWalkCount;
    private final Integer averageDistance;
    private final Integer totalDistance;
}
