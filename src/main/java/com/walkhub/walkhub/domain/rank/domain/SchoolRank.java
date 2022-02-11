package com.walkhub.walkhub.domain.rank.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(SchoolRankId.class)
public class SchoolRank {
    @Id
    private Long schoolId;

    @Id
    private LocalDate createdAt;

    @Id
    private String dateType;

    @Column(length = 20, unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String logoImageUrl;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Long userCount;

    @Column(nullable = false)
    private Integer walkCount;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer ranking;

}
