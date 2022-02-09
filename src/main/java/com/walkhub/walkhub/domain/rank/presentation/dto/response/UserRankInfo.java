package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRankInfo {
    private String accountId;

    private String name;

    private String agencyCode;

    private Integer grade;

    private Integer classNum;

    private String profileImageUrl;

    private Integer walkCount;

    private Integer ranking;
}
