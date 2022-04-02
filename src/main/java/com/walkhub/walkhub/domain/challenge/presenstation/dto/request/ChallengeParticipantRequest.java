package com.walkhub.walkhub.domain.challenge.presenstation.dto.request;

import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsOrder;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsScope;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ChallengeParticipantRequest {

    private final String name;

    @NotNull(message = "sort는 Null일 수 없습니다.")
    private final ChallengeParticipantsOrder sort;

    @NotNull(message = "userScope는 Null일 수 없습니다.")
    private final ChallengeParticipantsScope userScope;

    private final Integer grade;

    private final Integer classNum;

    @NotNull(message = "size는 Null일 수 없습니다.")
    private Long size;

}
