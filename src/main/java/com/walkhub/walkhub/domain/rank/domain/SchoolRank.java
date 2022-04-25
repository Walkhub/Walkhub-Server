package com.walkhub.walkhub.domain.rank.domain;

import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Cacheable
@IdClass(SchoolRankId.class)
public class SchoolRank {
    @Id
    private Long schoolId;

    @Id
    private LocalDate createdAt;

    @Id
    @Column(length = 5)
    private SchoolDateType dateType;

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

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private Integer ranking;

}
