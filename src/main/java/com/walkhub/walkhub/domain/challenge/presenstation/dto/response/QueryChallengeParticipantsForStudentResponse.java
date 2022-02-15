package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

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
    }
}
