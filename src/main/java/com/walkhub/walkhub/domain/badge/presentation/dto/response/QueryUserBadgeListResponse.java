package com.walkhub.walkhub.domain.badge.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryUserBadgeListResponse {

    private final List<DefaultBadgeResponse> userBadgeList;

    @Getter
    @Builder
    public static class DefaultBadgeResponse{
        private final Long badgeId;
        private final String name;
        private final String imageUrl;
    }
}
