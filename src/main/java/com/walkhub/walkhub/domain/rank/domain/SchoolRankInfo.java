package com.walkhub.walkhub.domain.rank.domain;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Immutable
@Table(name = "school_rank_info")
@Entity
public class SchoolRankInfo {

    @Id
    private String agencyCode;
    private String name;
    private String logoImageUrl;
    private Integer walkCount;
    private Integer ranking;

}
