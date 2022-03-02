package com.walkhub.walkhub.domain.badge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import lombok.Getter;

@Getter
public class DefaultBadgeVO {
    private final Long badgeId;
    private final String name;
    private final String imageUrl;
    private final BadgeType code;

    @QueryProjection
    public DefaultBadgeVO(Long badgeId, String name, String imageUrl, BadgeType code) {
        this.badgeId = badgeId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.code = code;
    }
}
