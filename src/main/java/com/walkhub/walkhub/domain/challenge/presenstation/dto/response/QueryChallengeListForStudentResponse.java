package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryChallengeListForStudentResponse {

    private final List<ChallengeResponse> challengeList;

    @Getter
    @Builder
    public static class ChallengeResponse {
        private final Long id;
        private final String name;
        private final String imageUrl;
        private final LocalDate startAt;
        private final LocalDate endAt;
        private final Integer goal;
        private final GoalScope goalScope;
        private final GoalType goalType;
        private final String award;
        private final Person writer;
        private final Long participantCount;
        private final List<Person> participantList;
    }

    @Getter
    @Builder
    public static class Person {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;
    }
}
