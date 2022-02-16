package com.walkhub.walkhub.domain.challenge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ShowChallengeVO {

    private final Long challengeId;
    private final String name;
    private final LocalDate startAt;
    private final LocalDate endAt;
    private final String imageUrl;
    private final UserScope userScope;
    private final GoalScope goalScope;
    private final GoalType goalType;
    private final Long writerId;
    private final String writerName;
    private final String profileImageUrl;

    @QueryProjection
    public ShowChallengeVO(Long challengeId, String name, LocalDate startAt, LocalDate endAt,
                           String imageUrl, UserScope userScope, GoalScope goalScope,
                           GoalType goalType, Long writerId, String writerName, String profileImageUrl) {
        this.challengeId = challengeId;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.imageUrl = imageUrl;
        this.userScope = userScope;
        this.goalScope = goalScope;
        this.goalType = goalType;
        this.writerId = writerId;
        this.writerName = writerName;
        this.profileImageUrl = profileImageUrl;
    }
}
