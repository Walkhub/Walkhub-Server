package com.walkhub.walkhub.domain.rank.domain.repository.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SchoolRankWriterVO {

    private Long schoolId;
    private LocalDate createdAt;
    private String dateType;
    private String name;
    private String logoImageUrl;
    private Long userCount;
    private Integer walkCount;
    private Integer ranking;

}
