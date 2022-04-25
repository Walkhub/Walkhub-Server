package com.walkhub.walkhub.domain.rank.domain;

import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Column(length = 5)
    private DateType dateType;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private UserRankScope scopeType;

    @NotNull
    @Size(max = 20)
    private String name;

    private Integer grade;

    private Integer classNum;

    @NotNull
    private String profileImageUrl;

    @NotNull
    private Integer walkCount;

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private Integer ranking;

    @NotNull
    private Long schoolId;
}