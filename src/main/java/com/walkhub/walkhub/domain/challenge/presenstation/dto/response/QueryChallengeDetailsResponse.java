package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class QueryChallengeDetailsResponse {

    private final String name;
    private final String content;
    private final UserScope userScope;
    private final Integer goal;
    private final GoalScope goalScope;
    private final GoalType goalType;
    private final String award;
    private final String imageUrl;
    private final LocalDate startAt;
    private final LocalDate endAt;
    private final Long participantCount;
    private final Boolean isMine;
    private final Boolean isParticipated;
    private final Integer successStandard;
    private final Writer writer;

    @Getter
    @Builder
    public static class Writer {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;
        private final Authority authority;
        private final String schoolName;
        private final Integer grade;
        private final Integer classNum;
    }
}
