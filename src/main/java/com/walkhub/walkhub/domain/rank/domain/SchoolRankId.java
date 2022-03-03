package com.walkhub.walkhub.domain.rank.domain;

import java.time.LocalDate;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class SchoolRankId implements Serializable {
    private Long schoolId;

    private LocalDate createdAt;

    private String dateType;
}
