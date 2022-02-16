package com.walkhub.walkhub.domain.badge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MyBadgeVo {

    private final Long badgeId;
    private final String name;
    private final String imageUrl;
    private final boolean isMine;

    @QueryProjection
    public MyBadgeVo(Long badgeId, String name,
                     String imageUrl, boolean isMine) {
        this.badgeId = badgeId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.isMine = isMine;
    }

}
