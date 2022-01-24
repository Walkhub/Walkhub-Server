package com.walkhub.walkhub.domain.challenge.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChallengeParticipantsListResponse {

    private final List<ChallengeParticipantsElement> challengeParticipantsList;

}
