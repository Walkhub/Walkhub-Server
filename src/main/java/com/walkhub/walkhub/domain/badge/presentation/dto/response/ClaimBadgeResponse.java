package com.walkhub.walkhub.domain.badge.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClaimBadgeResponse {

    private final List<BadgeResponse> badgeList;

    @Getter
    @Builder
    public static class BadgeResponse {
        private final Long badgeId;
        private final String name;
        private final String imageUrl;
    }
}
