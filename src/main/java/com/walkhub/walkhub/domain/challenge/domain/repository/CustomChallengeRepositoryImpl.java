package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.QChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.jpa.JPAExpressions.select;
import static com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus.challengeStatus;
import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.school.domain.QSchool.school;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class CustomChallengeRepositoryImpl implements CustomChallengeRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChallengeParticipantsVO> queryChallengeParticipantsList(Challenge challenge, SuccessScope successScope) {
        return jpaQueryFactory
                .selectFrom(user)
                .join(user.school, school)
                .leftJoin(user.section, section)
                .join(user.exerciseAnalyses, exerciseAnalysis)
                .join(user.challengeStatuses, challengeStatus)
                .join(challengeStatus.challenge)
                .on(challengeStatus.challenge.eq(challenge))
                .where(
                        successScopeFilter(challenge, successScope),
                        goalTypeFilter(challenge),
                        exerciseAnalysis.date.between(challenge.getStartAt(), challenge.getEndAt())
                )
                .orderBy(user.name.asc(), user.id.asc(), exerciseAnalysis.date.asc())
                .transform(groupBy(user.name, user.id)
                        .list(new QChallengeParticipantsVO(
                                user.id.as("userId"),
                                user.section.grade,
                                user.section.classNum,
                                user.number,
                                user.name,
                                user.profileImageUrl,
                                user.school.name.as("schoolName"),
                                Expressions.asNumber(select(exerciseAnalysis.count())
                                        .from(exerciseAnalysis)
                                        .where(
                                                goalTypeFilter(challenge),
                                                exerciseAnalysis.user.eq(user),
                                                exerciseAnalysis.date.between(challenge.getStartAt(), challenge.getEndAt())
                                        ))
                                        .goe(challenge.getSuccessStandard()).as("isSuccess"),
                                GroupBy.list(exerciseAnalysis.date))
                        )
                );
    }

    private BooleanExpression successScopeFilter(Challenge challenge, SuccessScope successScope) {
        switch (successScope) {
            case TRUE: {
                return Expressions.asNumber(select(exerciseAnalysis.count())
                        .from(exerciseAnalysis)
                        .where(
                                goalTypeFilter(challenge),
                                exerciseAnalysis.user.eq(user),
                                exerciseAnalysis.date.between(challenge.getStartAt(), challenge.getEndAt())
                        ))
                        .goe(challenge.getSuccessStandard());
            }
            case FALSE: {
                return Expressions.asNumber(select(exerciseAnalysis.count())
                        .from(exerciseAnalysis)
                        .where(
                                goalTypeFilter(challenge),
                                exerciseAnalysis.user.eq(user),
                                exerciseAnalysis.date.between(challenge.getStartAt(), challenge.getEndAt())
                        ))
                        .lt(challenge.getSuccessStandard());
            }
            default: {
                return null;
            }
        }
    }

    private BooleanExpression goalTypeFilter(Challenge challenge) {
        if (challenge.getGoalType() == GoalType.WALK) {
            return isChallengeSuccessByWalkCount(challenge);
        }

        return isChallengeSuccessByDistance(challenge);
    }

    private BooleanExpression isChallengeSuccessByWalkCount(Challenge challenge) {
        if (challenge.getGoalScope() == GoalScope.DAY) {
            return exerciseAnalysis.walkCount.goe(challenge.getGoal());
        }

        return JPAExpressions.select(exerciseAnalysis.walkCount.sum())
                .from(exerciseAnalysis)
                .where(
                        exerciseAnalysis.date.between(challenge.getStartAt(), challenge.getEndAt()),
                        exerciseAnalysis.user.eq(user)
                )
                .goe(challenge.getGoal());
    }

    private BooleanExpression isChallengeSuccessByDistance(Challenge challenge) {
        if (challenge.getGoalScope() == GoalScope.DAY) {
            return exerciseAnalysis.distance.goe(challenge.getGoal());
        }

        return JPAExpressions.select(exerciseAnalysis.distance.sum())
                .from(exerciseAnalysis)
                .where(
                        exerciseAnalysis.date.between(challenge.getStartAt(), challenge.getEndAt()),
                        exerciseAnalysis.user.eq(user)
                )
                .goe(challenge.getGoal());
    }

}
