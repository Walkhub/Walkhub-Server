package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRankInfo {
    private Long userId;

    private String name;

    private Long schoolId;

    private Integer grade;

    private Integer classNum;

    private String profileImageUrl;

    private Integer walkCount;

    private Integer ranking;
}
