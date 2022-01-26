package com.walkhub.walkhub.domain.challenge.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChallengeParticipantsListResponse {

    private final List<ChallengeParticipantsElement> challengeParticipantsList;

    @Getter
    @Builder
    public static class ChallengeParticipantsElement {
        private final Long id;

        private final String name;

        private final String profileImageUrl;

        private final String schoolName;
    }

}
