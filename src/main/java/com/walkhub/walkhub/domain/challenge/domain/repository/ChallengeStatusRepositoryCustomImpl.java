package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.*;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsOrder;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsScope;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.walkhub.walkhub.domain.challenge.domain.QChallenge.challenge;
import static com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus.challengeStatus;
import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.school.domain.QSchool.school;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class ChallengeStatusRepositoryCustomImpl implements ChallengeStatusRepositoryCustom {
    private static final int PARTICIPANTS_SIZE = 9;

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
    public void deleteNotOverChallengeStatusByUserId(Long userId) {
        queryFactory
                .delete(challengeStatus)
                .where(challengeStatus.user.id.eq(userId),
                        challengeStatus.challenge.id.in(
                                JPAExpressions
                                        .select(challenge.id)
                                        .from(challenge)
                                        .where(challenge.userScope.eq(UserScope.CLASS).or(challenge.userScope.eq(UserScope.GRADE)))
                                        .where(challenge.endAt.after(LocalDate.now()).and(challengeStatus.user.id.eq(userId)))
                        )
                )
                .execute();
    }

    @Override
    public List<ShowChallengeVO> getAllChallengesByUser(User user1) {
        return queryFactory
                .select(new QShowChallengeVO(
                        challenge.id.as("challengeId"),
                        challenge.name,
                        challenge.startAt,
                        challenge.endAt,
                        challenge.imageUrl,
                        challenge.userScope,
                        challenge.goalScope,
                        challenge.goalType,
                        user.id.as("userId"),
                        user.name.as("writerName"),
                        user.profileImageUrl.as("profileImageUrl")
                ))
                .from(challenge)
                .join(challenge.user, user)
                .join(challengeStatus)
                .on(challengeStatus.challenge.eq(challenge))
                .where(challengeStatus.user.eq(user1))
                .fetch();
    }

    private BooleanExpression challengeDateFilter(Challenge challenge) {
        return exerciseAnalysis.date.goe(challenge.getStartAt())
                .and(exerciseAnalysis.date.goe(challengeStatus.createdAt))
                .and(exerciseAnalysis.date.loe(challenge.getEndAt()));
    }

    @Override
    public List<ChallengeProgressVO> queryChallengeProgress(
            Challenge challenge,
            ChallengeParticipantsScope participantsScope,
            ChallengeParticipantsOrder participantsOrder,
            SuccessScope successScope,
            Long page
    ) {
        return queryFactory.select(new QChallengeProgressVO(
                        user.id,
                        user.name,
                        section.grade,
                        section.classNum,
                        user.number,
                        school.name,
                        user.profileImageUrl,
                        Expressions.asNumber(
                                select(exerciseAnalysis.walkCount.sum())
                                        .from(exerciseAnalysis)
                                        .where(exerciseAnalysis.user.eq(user),
                                                challengeDateFilter(challenge))
                        ).intValue(),
                        getChallengeProgress(challenge).multiply(100).round().longValue(),
                        exerciseAnalysis.date.count().goe(challenge.getSuccessStandard()),
                        new CaseBuilder()
                                .when(exerciseAnalysis.date.count().goe(challenge.getSuccessStandard()))
                                .then(exerciseAnalysis.date.max())
                                .otherwise(Expressions.nullExpression())))
                .from(user)
                .offset(page == null ? 0 : page * PARTICIPANTS_SIZE)
                .limit(PARTICIPANTS_SIZE)
                .leftJoin(user.section, section)
                .join(user.school, school)
                .join(user.challengeStatuses, challengeStatus)
                .leftJoin(user.exerciseAnalyses, exerciseAnalysis)
                .on(challengeDateFilter(challenge),
                        isChallengeSuccessFilter(challenge))
                .where(userScopeFilter(participantsScope))
                .orderBy(challengeParticipantsOrder(participantsOrder))
                .having(challengeSuccessFilter(successScope, challenge))
                .groupBy(user.id)
                .fetch();
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

    private BooleanExpression userScopeFilter(ChallengeParticipantsScope challengeParticipantsScope) {
        if (challengeParticipantsScope == ChallengeParticipantsScope.STUDENT) {
            return user.authority.eq(Authority.USER);
        } else if (challengeParticipantsScope == ChallengeParticipantsScope.TEACHER) {
            return user.authority.eq(Authority.TEACHER);
        } else {
            return null;
        }
    }

    private BooleanExpression challengeSuccessFilter(SuccessScope successScope, Challenge challenge) {
        if (successScope == SuccessScope.TRUE) {
            return exerciseAnalysis.date.count().goe(challenge.getSuccessStandard());
        } else if (successScope == SuccessScope.FALSE) {
            return exerciseAnalysis.date.count().lt(challenge.getSuccessStandard());
        } else {
            return null;
        }
    }

    private NumberExpression<Long> getChallengeProgress(Challenge challenge) {
        NumberExpression<Integer> successProgress;

        if (challenge.getGoalScope() == GoalScope.ALL) {
            if (challenge.getGoalType() == GoalType.DISTANCE) {
                successProgress = exerciseAnalysis.distance.sum();
            } else {
                successProgress = exerciseAnalysis.walkCount.sum();
            }
            return successProgress.divide(challenge.getGoal()).longValue();
        } else {
            return exerciseAnalysis.date.count().divide(challenge.getSuccessStandard());
        }
    }

    private OrderSpecifier<?> challengeParticipantsOrder(ChallengeParticipantsOrder challengeParticipantsOrder) {
        if (challengeParticipantsOrder == ChallengeParticipantsOrder.SUCCESS_DATE) {
            return new OrderSpecifier<>(Order.DESC, exerciseAnalysis.date.max());
        } else if (challengeParticipantsOrder == ChallengeParticipantsOrder.PROGRESS) {
            return new OrderSpecifier<>(Order.DESC, exerciseAnalysis.walkCount.sum().divide(exerciseAnalysis.date.count()));
        } else if (challengeParticipantsOrder == ChallengeParticipantsOrder.SCHOOL_NAME) {
            return new OrderSpecifier<>(Order.ASC, school.name);
        } else {
            return new OrderSpecifier<>(Order.ASC, user.name);
        }
    }
}
