package com.walkhub.walkhub.domain.rank.domain;

import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class SchoolRankId implements Serializable {
    private Long schoolId;

    private ZonedDateTime createdAt;

    private String dateType;
}
