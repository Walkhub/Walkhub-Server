package com.walkhub.walkhub.domain.challenge.presenstation.dto.request;

import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsOrder;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsScope;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueryChallengeProgressRequest {
    private final ChallengeParticipantsScope participantsScope;
    private final ChallengeParticipantsOrder participantsOrder;
    private final SuccessScope successScope;
    private final Long page;
}
