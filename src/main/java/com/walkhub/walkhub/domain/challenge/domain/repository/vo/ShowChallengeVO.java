package com.walkhub.walkhub.domain.challenge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ShowChallengeVO {

    private final Long challengeId;
    private final String name;
    private final LocalDate startAt;
    private final LocalDate endAt;
    private final Integer goal;
    private final GoalScope goalScope;
    private final GoalType goalType;
    private final String award;
    private final Long writerId;
    private final String writerName;
    private final String writerProfileImageUrl;
    private final Long participantCount;
    // 참여자 3명 추가
    private final List<RelatedChallengeParticipantsVO> participantList;

    @QueryProjection
    public ShowChallengeVO(Long challengeId, String name, LocalDate startAt, LocalDate endAt,
                           Integer goal, GoalScope goalScope, GoalType goalType, String award,
                           Long writerId, String writerName, String writerProfileImageUrl,
                           Long participantCount, List<RelatedChallengeParticipantsVO> participantList) {
        this.challengeId = challengeId;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.goal = goal;
        this.goalScope = goalScope;
        this.goalType = goalType;
        this.award = award;
        this.writerId = writerId;
        this.writerName = writerName;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.participantCount = participantCount;
        this.participantList = participantList;
    }
}
