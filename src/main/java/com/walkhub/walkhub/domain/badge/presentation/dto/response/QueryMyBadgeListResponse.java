package com.walkhub.walkhub.domain.badge.presentation.dto.response;

import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryMyBadgeListResponse {
    private final List<MyBadge> badgeLists;

    @Getter
    @Builder
    public static class MyBadge {
        private final Long badgeId;
        private final String name;
        private final String imageUrl;
        private final BadgeType code;
        private final Boolean isMine;
        private final String condition;
    }
}
