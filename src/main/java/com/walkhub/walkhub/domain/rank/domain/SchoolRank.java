package com.walkhub.walkhub.domain.rank.domain;

import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SchoolRank extends BaseTimeEntity {

    @Id
    @Column(length = 7)
    private String agencyCode;

    @Column(length = 20, unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "TINYINT", unique = true, nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private Integer allWalkCount;

    @Column(nullable = false)
    private Integer averageWalkCount;

    private String logoImageUrl;

    @Builder
    public SchoolRank(String agencyCode, String name, Integer rank, Integer allWalkCount, Integer averageWalkCount) {
        this.agencyCode = agencyCode;
        this.name = name;
        this.rank = rank;
        this.allWalkCount = allWalkCount;
        this.averageWalkCount = averageWalkCount;
    }
}
