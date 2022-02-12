package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryChallengeParticipantsForTeacherResponse {

    private final List<ChallengeParticipants> challengeParticipantsList;

    @Getter
    @Builder
    public static class ChallengeParticipants {
        private final Long userId;
        private final Integer grade;
        private final Integer classNum;
        private final Integer number;
        private final String name;
        private final String profileImageUrl;
        private final String schoolName;
        private final List<LocalDate> successDate;
        private final Boolean isSuccess;
    }

}
