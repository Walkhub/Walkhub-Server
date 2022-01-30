package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserRankListResponse {

    private final UserRankResponse myRank;
    private final List<UserRankResponse> rankList;

    @Getter
    @Builder
    public static class UserRankResponse {
        private final String accountId;
        private final String name;
        private final Integer grade;
        private final Integer classNum;
        private final Integer ranking;
        private final String profileImageUrl;
        private final Long walkCount;

        @QueryProjection
        public UserRankResponse(String accountId, String name, Integer grade, Integer classNum, Integer ranking, String profileImageUrl, Long walkCount) {
            this.accountId = accountId;
            this.name = name;
            this.grade = grade;
            this.classNum = classNum;
            this.ranking = ranking;
            this.profileImageUrl = profileImageUrl;
            this.walkCount = walkCount;
        }
    }
}
