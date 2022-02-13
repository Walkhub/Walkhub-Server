package com.walkhub.walkhub.domain.rank.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@IdClass(UserRankId.class)
@Builder
public class UserRank {
    @Id
    private Long userId;

    @Id
    private LocalDate createdAt;

    @Id
    private String dateType;

    @Id
    private String scopeType;

    @Column(length = 20, nullable = false)
    private String name;

    private Integer grade;

    private Integer classNum;

    private String profileImageUrl;

    @Column(nullable = false)
    private Integer walkCount;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer ranking;

    private Long schoolId;
}