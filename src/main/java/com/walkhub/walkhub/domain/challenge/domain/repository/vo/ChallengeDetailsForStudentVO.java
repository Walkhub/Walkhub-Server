package com.walkhub.walkhub.domain.challenge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ChallengeDetailsForStudentVO {

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
    private final Integer successStandard;
    private final Long writerUserId;
    private final String writerName;
    private final String writerProfileImageUrl;
    private final Boolean isMine;
    private final Boolean isParticipated;
    private final Long participantCount;
//    private final List<RelatedChallengeParticipantsVO> participantList;

    @QueryProjection
    public ChallengeDetailsForStudentVO(String name, String content, UserScope userScope,
                                        Integer goal, GoalScope goalScope, GoalType goalType,
                                        String award, String imageUrl, LocalDate startAt, LocalDate endAt, Integer successStandard,
                                        Long writerUserId, String writerName, String writerProfileImageUrl,
                                        Boolean isMine, Boolean isParticipated,
                                        Long participantCount) {
        this.name = name;
        this.content = content;
        this.userScope = userScope;
        this.goal = goal;
        this.goalScope = goalScope;
        this.goalType = goalType;
        this.award = award;
        this.imageUrl = imageUrl;
        this.startAt = startAt;
        this.endAt = endAt;
        this.successStandard = successStandard;
        this.writerUserId = writerUserId;
        this.writerName = writerName;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.isMine = isMine;
        this.isParticipated = isParticipated;
        this.participantCount = participantCount;
//        this.participantList = participantList;
    }
}
