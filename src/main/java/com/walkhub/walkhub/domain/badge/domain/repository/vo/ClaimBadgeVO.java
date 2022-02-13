package com.walkhub.walkhub.domain.badge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ClaimBadgeVO {
    private final Long badgeId;
    private final String name;
    private final String imageUrl;

    @QueryProjection
    public ClaimBadgeVO(Long badgeId, String name, String imageUrl) {
        this.badgeId = badgeId;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
