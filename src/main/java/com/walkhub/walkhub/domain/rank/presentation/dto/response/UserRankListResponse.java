package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserRankListResponse {

    private final List<UserRankResponse> rankList;

    @Getter
    @Builder
    public static class UserRankResponse {
        private final Long userId;
        private final String name;
        private final Integer ranking;
        private final String profileImageUrl;
        private final Integer walkCount;
    }
}
