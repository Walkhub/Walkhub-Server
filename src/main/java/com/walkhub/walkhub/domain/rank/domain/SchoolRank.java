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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    private String logoImageUrl;

    @NotNull
    @ColumnDefault("0")
    private Long userCount;

    @NotNull
    private Integer walkCount;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer ranking;

}
