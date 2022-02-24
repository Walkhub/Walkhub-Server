package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class QueryChallengeProgressResponse {

    private final String name;
    private final Long userId;
    private final String content;
    private final String imageUrl;
    private final String writerName;
    private final String writerProfileImageUrl;
    private final String award;
    private final LocalDate startAt;
    private final LocalDate endAt;
    private final Integer goal;
    private final GoalScope goalScope;
    private final UserScope userScope;
    private final GoalType goalType;
    private final Integer classNum;
    private final Integer grade;
    private final Integer successStandard;
    private final Long count;
    private final List<UserChallengeProgressResponse> userResponse;

    @Getter
    @Builder
    public static class UserChallengeProgressResponse {
        private final Long userId;
        private final String userName;
        private final Integer grade;
        private final Integer classNum;
        private final Integer number;
        private final String schoolName;
        private final String profileImageUrl;
        private final Integer totalWalkCount;
        private final Long progress;
        private final Boolean isSuccess;
        private final LocalDate successDate;
    }

}
