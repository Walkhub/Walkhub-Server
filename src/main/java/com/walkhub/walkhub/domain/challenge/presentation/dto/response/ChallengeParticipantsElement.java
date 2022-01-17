package com.walkhub.walkhub.domain.challenge.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeParticipantsElement {

    private final Long id;

    private final String name;

    private final String gcn;

    private final String profileImageUrl;

}
