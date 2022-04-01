package com.walkhub.walkhub.domain.challenge.presenstation.dto.request;

import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsOrder;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsScope;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ChallengeParticipantRequest {

    private String keyword;

    @NotNull(message = "sort는 Null일 수 없습니다.")
    private ChallengeParticipantsOrder sort;

    @NotNull(message = "userScope는 Null일 수 없습니다.")
    private ChallengeParticipantsScope userScope;

    private Integer grade;

    private Integer classNum;

    @NotNull(message = "size는 Null일 수 없습니다.")
    private Long size;

}
