package com.walkhub.walkhub.domain.challenge.presenstation.dto.request;

import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsOrder;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsScope;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChallengeParticipantRequest {

    private String keyword;
    private ChallengeParticipantsOrder sort;
    private ChallengeParticipantsScope userScope;
    private Integer grade;
    private Integer classNum;
    private Long size;

}
