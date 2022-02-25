package com.walkhub.walkhub.domain.rank.domain.repository.vo;

import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserRankWriterVO {
    private Long userId;
    private LocalDate createdAt;
    private String dateType;
    private String scopeType;
    private String name;
    private Integer grade;
    private Integer classNum;
    private String profileImageUrl;
    private Integer walkCount;
    private Integer ranking;
    private Long schoolId;
}
