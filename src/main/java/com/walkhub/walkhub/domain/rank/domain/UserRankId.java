package com.walkhub.walkhub.domain.rank.domain;

import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class UserRankId implements Serializable {
    private Long userId;

    private LocalDate createdAt;

    private DateType dateType;

    private UserRankScope scopeType;
}