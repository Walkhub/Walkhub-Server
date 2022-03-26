package com.walkhub.walkhub.domain.rank.domain.repository.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserRankWriterVO {
    private final Long userId;
    private final LocalDate createdAt;
    private final String dateType;
    private final String scopeType;
    private final String name;
    private final Integer grade;
    private final Integer classNum;
    private final String profileImageUrl;
    private final Integer walkCount;
    private final Integer ranking;
    private final Long schoolId;
}
