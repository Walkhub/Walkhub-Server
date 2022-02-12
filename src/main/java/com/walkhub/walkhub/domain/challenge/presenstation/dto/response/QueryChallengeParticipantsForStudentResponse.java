package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryChallengeParticipantsForStudentResponse {
    private final Integer participantCount;
    private final List<RelatedChallengeParticipants> relatedChallengeParticipantList;

    @Getter
    @Builder
    public static class RelatedChallengeParticipants {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;

        @QueryProjection
        public RelatedChallengeParticipants(Long userId, String name, String profileImageUrl) {
            this.userId = userId;
            this.name = name;
            this.profileImageUrl = profileImageUrl;
        }
    }
}
