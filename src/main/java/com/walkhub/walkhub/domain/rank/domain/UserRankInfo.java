package com.walkhub.walkhub.domain.rank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Immutable
@Table(name = "user_rank_info")
@Entity
public class UserRankInfo {

    @Id
    private String accountId;

    private String name;

    private Integer grade;

    private Integer classNum;

    private String profileImageUrl;

    private Long walkCount;

    private Integer ranking;

    private String agencyCode;

    private LocalDateTime createDate;

}
