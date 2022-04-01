package com.walkhub.walkhub.domain.challenge.presenstation.dto.request;

import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsOrder;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsScope;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChallengeParticipantRequest {

    private final String keyword;
    private final ChallengeParticipantsOrder sort;
    private final ChallengeParticipantsScope userScope;
    private final Integer grade;
    private final Integer classNum;
    private final Long size;

}
