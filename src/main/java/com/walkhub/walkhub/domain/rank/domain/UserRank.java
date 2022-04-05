package com.walkhub.walkhub.domain.rank.domain;

import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Cacheable
@IdClass(UserRankId.class)
@Builder
public class UserRank {
    @Id
    private Long userId;

    @Id
    private LocalDate createdAt;

    @Id
    @Enumerated(EnumType.STRING)
    private DateType dateType;

    @Id
    @Enumerated(EnumType.STRING)
    private UserRankScope scopeType;

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