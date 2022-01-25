package com.walkhub.walkhub.domain.rank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Immutable
@Table(name = "user_rank")
@Entity
public class UserRank {

    @Id
    private String accountId;

    private String name;

    private Integer grade;

    private Integer classNum;

    private String profileImageUrl;

    private Long walkCount;

    private Integer rank;

    private String agencyCode;

}
