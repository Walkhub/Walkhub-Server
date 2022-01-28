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
    private Integer ranking;

    @Column(nullable = false)
    private Integer walkCount;

    private String logoImageUrl;

    @Builder
    public SchoolRank(String agencyCode, String name, Integer ranking,
                      Integer walkCount, String logoImageUrl) {
        this.agencyCode = agencyCode;
        this.name = name;
        this.ranking = ranking;
        this.walkCount = walkCount;
        this.logoImageUrl = logoImageUrl;
    }
}
