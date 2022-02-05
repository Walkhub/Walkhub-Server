package com.walkhub.walkhub.domain.rank.domain;

import com.walkhub.walkhub.domain.rank.domain.type.DateType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@IdClass(UserRankId.class)
@Builder
public class UserRank {
    @Id
    private String accountId;

    @Id
    private LocalDate createdAt;

    @Id
    private String dateType;

    @Column(length = 20, nullable = false)
    private String name;

    private Integer grade;

    private Integer classNum;

    private String profileImageUrl;

    @Column(nullable = false)
    private Integer walkCount;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer ranking;

    @Column(length = 7)
    private String agencyCode;
}