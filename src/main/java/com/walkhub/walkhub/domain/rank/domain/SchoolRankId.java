package com.walkhub.walkhub.domain.rank.domain;

import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class SchoolRankId implements Serializable {
    private Long schoolId;

    private LocalDate createdAt;

    private SchoolDateType dateType;
}
