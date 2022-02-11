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
        private final Long userId;
        private final String name;
        private final Integer grade;
        private final Integer classNum;
        private final Integer ranking;
        private final String profileImageUrl;
        private final Integer walkCount;

        @QueryProjection
        public UserRankResponse(Long userId, String name, Integer grade, Integer classNum, Integer ranking, String profileImageUrl, Integer walkCount) {
            this.userId = userId;
            this.name = name;
            this.grade = grade;
            this.classNum = classNum;
            this.ranking = ranking;
            this.profileImageUrl = profileImageUrl;
            this.walkCount = walkCount;
        }
    }
}
