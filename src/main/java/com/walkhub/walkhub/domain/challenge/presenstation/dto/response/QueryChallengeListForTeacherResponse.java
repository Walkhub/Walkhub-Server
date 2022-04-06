package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryChallengeListForTeacherResponse {

    private final List<ChallengeResponse> challengeList;

    @Getter
    @Builder
    public static class ChallengeResponse{
        private final Long id;
        private final String name;
        private final String imageUrl;
        private final LocalDate startAt;
        private final LocalDate endAt;
        private final String award;
        private final Integer goal;
        private final UserScope userScope;
        private final GoalScope goalScope;
        private final GoalType goalType;
        private final PersonResponse writer;
        private final Long participantCount;
        private final Boolean isProgress;
    }
}
