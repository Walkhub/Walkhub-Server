package com.walkhub.walkhub.domain.rank.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SchoolRankId implements Serializable {
    @Column(length = 7)
    private String agencyCode;

    private LocalDateTime createdAt;
}
