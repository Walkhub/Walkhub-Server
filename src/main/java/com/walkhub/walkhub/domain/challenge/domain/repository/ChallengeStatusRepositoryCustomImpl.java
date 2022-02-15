package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.QRelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.RelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.school.domain.School;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.QChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus.challengeStatus;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.jpa.JPAExpressions.select;
import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.school.domain.QSchool.school;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class ChallengeStatusRepositoryCustomImpl implements ChallengeStatusRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Integer getParticipantsCountByChallengeId(Long challengeId) {
        List<ChallengeStatus> participantsList = queryFactory
                .select(challengeStatus)
                .from(challengeStatus)
                .where(challengeStatus.challenge.id.eq(challengeId))
                .fetch();
        return participantsList.size();
    }

    @Override
    public List<RelatedChallengeParticipantsVO> getRelatedChallengeParticipantsList(Long challengeId, School school, Integer grade, Integer classNum) {
        return queryFactory
                .select(new QRelatedChallengeParticipantsVO(
                        user.id.as("userId"),
                        user.name,
                        user.profileImageUrl
                ))
                .from(user)
                .join(challengeStatus)
                .on(challengeStatus.user.eq(user))
                .join(section)
                .on(section.id.eq(user.section.id))
                .where(
                        challengeStatus.challenge.id.eq(challengeId),
                        user.school.eq(school)
                )
                .orderBy(
                        section.grade.subtract(grade).abs().asc(),
                        section.classNum.subtract(classNum).abs().asc(),
                        user.school.id.subtract(school.getId()).abs().asc()
                )
                .limit(3)
                .fetch();
    }

    @Override
    public List<ChallengeParticipantsVO> queryChallengeParticipantsList(Challenge challenge, SuccessScope successScope, Long page) {
        final long size = 20L;
        return queryFactory
                .selectFrom(user)
                .join(user.school, school)
                .leftJoin(user.section, section)
                .join(user.exerciseAnalyses, exerciseAnalysis)
                .join(user.challengeStatuses, challengeStatus)
                .join(challengeStatus.challenge)
                .on(challengeStatus.challenge.eq(challenge))
                .where(
                        successScopeFilter(challenge, successScope),
                        isChallengeSuccessFilter(challenge),
                        challengeDateFilter(challenge)
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
                                                exerciseAnalysis.user.eq(user),
                                                isChallengeSuccessFilter(challenge),
                                                challengeDateFilter(challenge)
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
                                exerciseAnalysis.user.eq(user),
                                isChallengeSuccessFilter(challenge),
                                challengeDateFilter(challenge)
                        ))
                        .goe(challenge.getSuccessStandard());
            }
            case FALSE: {
                return Expressions.asNumber(select(exerciseAnalysis.count())
                        .from(exerciseAnalysis)
                        .where(
                                exerciseAnalysis.user.eq(user),
                                isChallengeSuccessFilter(challenge),
                                challengeDateFilter(challenge)
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
                        challengeDateFilter(challenge)
                )
                .goe(challenge.getGoal());
    }

    private BooleanExpression challengeDateFilter(Challenge challenge) {
        return exerciseAnalysis.date.goe(challenge.getStartAt())
                .and(exerciseAnalysis.date.goe(challengeStatus.createdAt))
                .and(exerciseAnalysis.date.loe(challenge.getEndAt()));
    }
}
