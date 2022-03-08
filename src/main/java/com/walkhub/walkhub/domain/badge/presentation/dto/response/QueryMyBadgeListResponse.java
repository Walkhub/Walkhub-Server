package com.walkhub.walkhub.domain.badge.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryMyBadgeListResponse {
    private final List<MyBadge> myBadgeList;

    @Getter
    @Builder
    public static class MyBadge {
        private final Long badgeId;
        private final String name;
        private final String imageUrl;
        private final Boolean isMine;
        private final String condition;
    }
}
