package com.walkhub.walkhub.domain.rank.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "user_rank_by_school")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Immutable
@Builder
@NamedStoredProcedureQuery(name = "UserRankBySchool.selectUserRankBySchoolAndDateType", procedureName = "SELECT_USER_RANK_BY_SCHOOL_AND_DATETYPE")
public class UserRankBySchool {
    @Id
    private String accountId;

    private String name;

    private String agencyCode;

    private Integer grade;

    private Integer classNum;

    private String profileImageUrl;

    private Integer walkCount;

    private Integer ranking;
}
