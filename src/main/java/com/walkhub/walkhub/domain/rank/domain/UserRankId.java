package com.walkhub.walkhub.domain.rank.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class UserRankId implements Serializable {
    private String accountId;

    private LocalDate createdAt;

    private String dateType;

    private String scopeType;
}