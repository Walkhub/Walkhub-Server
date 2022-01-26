package com.walkhub.walkhub.domain.rank.job.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolRankDto {

    private Integer rank;
    private String agencyCode;
    private String name;
    private String logoImageUrl;
    private Integer allWalkCount;
    private Integer averageWalkCount;

}
