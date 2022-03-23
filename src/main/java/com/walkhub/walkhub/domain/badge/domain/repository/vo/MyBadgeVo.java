package com.walkhub.walkhub.domain.badge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import lombok.Getter;

@Getter
public class MyBadgeVo {

    private final Long badgeId;
    private final String name;
    private final String imageUrl;
    private final BadgeType code;
    private final Boolean isMine;
    private final String condition;

    @QueryProjection
    public MyBadgeVo(Long badgeId, String name, String imageUrl, BadgeType code, String condition, Boolean isMine) {
        this.badgeId = badgeId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.code = code;
        this.condition = condition;
        this.isMine = isMine;
    }

}
