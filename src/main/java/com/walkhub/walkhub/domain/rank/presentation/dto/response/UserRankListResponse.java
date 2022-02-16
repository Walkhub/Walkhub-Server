package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRankListResponse {

    private final UserRankResponse myRank;
    private final List<UserRankResponse> rankList;

    @Getter
    @Builder
    public static class UserRankResponse {
        private final Long userId;
        private final String name;
        private final Integer grade;
        private final Integer classNum;
        private final Integer ranking;
        private final String profileImageUrl;
        private final Integer walkCount;
    }
}
