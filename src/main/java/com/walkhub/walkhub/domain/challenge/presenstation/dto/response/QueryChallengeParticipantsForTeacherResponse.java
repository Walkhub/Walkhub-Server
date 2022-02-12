package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
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

        @QueryProjection
        public ChallengeParticipants(Long userId, Integer grade, Integer classNum, Integer number,
                                     String name, String profileImageUrl, String schoolName,
                                     List<LocalDate> successDate, Boolean isSuccess) {
            this.userId = userId;
            this.grade = grade;
            this.classNum = classNum;
            this.number = number;
            this.name = name;
            this.profileImageUrl = profileImageUrl;
            this.schoolName = schoolName;
            this.successDate = successDate;
            this.isSuccess = isSuccess;
        }
    }

}
