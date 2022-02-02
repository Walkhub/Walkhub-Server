package com.walkhub.walkhub.domain.rank.domain;

import com.walkhub.walkhub.domain.user.domain.GroupId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(SchoolRankId.class)
public class SchoolRank {
    @Id
    @Column(length = 7)
    private String agencyCode;

    @Id
    private LocalDateTime createdAt;

    @Column(length = 20, unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "TINYINT", unique = true, nullable = false)
    private Integer ranking;

    @Column(nullable = false)
    private Integer walkCount;

    private String logoImageUrl;

    @Builder
    public SchoolRank(String agencyCode, LocalDateTime createdAt, String name, Integer ranking,
                      Integer walkCount, String logoImageUrl) {
        this.agencyCode = agencyCode;
        this.createdAt = createdAt;
        this.name = name;
        this.ranking = ranking;
        this.walkCount = walkCount;
        this.logoImageUrl = logoImageUrl;
    }
}
