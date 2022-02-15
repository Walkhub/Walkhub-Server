package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
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
    public List<ChallengeParticipantsVO> queryChallengeParticipantsList(Challenge challenge, SuccessScope successScope, Long page) {
        final long size = 20L;
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
                        isChallengeSuccessFilter(challenge),exerciseAnalysis.date.goe(challenge.getStartAt()),
                        exerciseAnalysis.date.goe(challengeStatus.createdAt),
                        exerciseAnalysis.date.loe(challenge.getEndAt())
                )
                .offset(page * size)
                .limit(size)
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
                                                isChallengeSuccessFilter(challenge),
                                                exerciseAnalysis.user.eq(user),
                                                exerciseAnalysis.date.goe(challenge.getStartAt()),
                                                exerciseAnalysis.date.goe(challengeStatus.createdAt),
                                                exerciseAnalysis.date.loe(challenge.getEndAt())
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
                                isChallengeSuccessFilter(challenge),
                                exerciseAnalysis.user.eq(user),
                                exerciseAnalysis.date.goe(challenge.getStartAt()),
                                exerciseAnalysis.date.goe(challengeStatus.createdAt),
                                exerciseAnalysis.date.loe(challenge.getEndAt())
                        ))
                        .goe(challenge.getSuccessStandard());
            }
            case FALSE: {
                return Expressions.asNumber(select(exerciseAnalysis.count())
                        .from(exerciseAnalysis)
                        .where(
                                isChallengeSuccessFilter(challenge),
                                exerciseAnalysis.user.eq(user),
                                exerciseAnalysis.date.goe(challenge.getStartAt()),
                                exerciseAnalysis.date.goe(challengeStatus.createdAt),
                                exerciseAnalysis.date.loe(challenge.getEndAt())
                        ))
                        .lt(challenge.getSuccessStandard());
            }
            default: {
                return null;
            }
        }
    }

    private BooleanExpression isChallengeSuccessFilter(Challenge challenge) {
        if (challenge.getGoalScope() == GoalScope.DAY) {
            return isChallengeSuccessInDayScope(challenge);
        }

        return isChallengeSuccessInAllScope(challenge);
    }

    private BooleanExpression isChallengeSuccessInDayScope(Challenge challenge) {
        NumberPath<Integer> exerciseAmount = exerciseAnalysis.distance;

        if (challenge.getGoalType() == GoalType.WALK) {
            exerciseAmount = exerciseAnalysis.walkCount;
        }

        return exerciseAmount.goe(challenge.getGoal());
    }

    private BooleanExpression isChallengeSuccessInAllScope(Challenge challenge) {
        NumberPath<Integer> exerciseAmount = exerciseAnalysis.distance;

        if (challenge.getGoalType() == GoalType.WALK) {
            exerciseAmount = exerciseAnalysis.walkCount;
        }

        return JPAExpressions.select(exerciseAmount.sum())
                .from(exerciseAnalysis)
                .where(
                        exerciseAnalysis.user.eq(user),
                        exerciseAnalysis.date.goe(challenge.getStartAt()),
                        exerciseAnalysis.date.goe(challengeStatus.createdAt),
                        exerciseAnalysis.date.loe(challenge.getEndAt())
                )
                .goe(challenge.getGoal());
    }

}
