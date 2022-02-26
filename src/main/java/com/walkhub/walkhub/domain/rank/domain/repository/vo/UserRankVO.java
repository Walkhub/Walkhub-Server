package com.walkhub.walkhub.domain.rank.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserRankVO {
    private final Long userId;
    private final String name;
    private final Integer ranking;
    private final String profileImageUrl;
    private final Integer walkCount;

    @QueryProjection
    public UserRankVO(Long userId, String name, Integer ranking, String profileImageUrl, Integer walkCount) {
        this.userId = userId;
        this.name = name;
        this.ranking = ranking;
        this.profileImageUrl = profileImageUrl;
        this.walkCount = walkCount;
    }
}
