package com.walkhub.walkhub.domain.challenge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ShowParticipatedChallengeVO {

    private final Long challengeId;
    private final String name;
    private final LocalDate startAt;
    private final LocalDate endAt;
    private final Integer goal;
    private final GoalScope goalScope;
    private final GoalType goalType;
    private final String award;
    private final Integer totalWalkCount;
    private final Long writerId;
    private final String writerName;
    private final String writerProfileImageUrl;

    @QueryProjection
    public ShowParticipatedChallengeVO(Long challengeId, String name, LocalDate startAt, LocalDate endAt,
                                       Integer goal, GoalScope goalScope, GoalType goalType, String award, Integer totalWalkCount,
                                       Long writerId, String writerName, String writerProfileImageUrl) {
        this.challengeId = challengeId;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.goal = goal;
        this.goalScope = goalScope;
        this.goalType = goalType;
        this.award = award;
        this.totalWalkCount = totalWalkCount;
        this.writerId = writerId;
        this.writerName = writerName;
        this.writerProfileImageUrl = writerProfileImageUrl;
    }
}
