package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Id;

@Getter
@Builder
public class SchoolRankInfo {
    private final Long schoolId;

    private final String name;

    private final String logoImageUrl;

    private final Long userCount;

    private final Integer walkCount;

    private final Integer ranking;

}
