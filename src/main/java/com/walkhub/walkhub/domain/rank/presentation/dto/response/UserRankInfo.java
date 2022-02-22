package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRankInfo {
    private final Long userId;

    private final String name;

    private final Long schoolId;

    private final Integer grade;

    private final Integer classNum;

    private final String profileImageUrl;

    private final Integer walkCount;

    private final Integer ranking;
}
