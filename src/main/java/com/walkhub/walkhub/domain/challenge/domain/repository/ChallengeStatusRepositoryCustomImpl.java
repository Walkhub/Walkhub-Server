package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForTeacherVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.QChallengeDetailsForTeacherVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.QShowParticipatedChallengeVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowParticipatedChallengeVO;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsOrder;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsScope;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    public List<ShowParticipatedChallengeVO> getParticipatedChallengesByUser(User userParam) {
        return queryFactory
                .select(new QShowParticipatedChallengeVO(
                        challenge.id.as("challengeId"),
                        challenge.name,
                        challenge.imageUrl,
                        challenge.startAt,
                        challenge.endAt,
                        challenge.goal,
                        challenge.goalScope,
                        challenge.goalType,
                        challenge.award,
                        new CaseBuilder()
                                .when(challenge.goalType.eq(GoalType.WALK))
                                .then(exerciseAnalysis.walkCount.sum())
                                .otherwise(exerciseAnalysis.distance.sum()),
                        user.id.as("writerId"),
                        user.name.as("writerName"),
                        user.profileImageUrl.as("writerProfileImageUrl")
                ))
                .from(challenge)
                .join(challenge.user, user)
                .leftJoin(challengeStatus)
                .on(challengeStatus.challenge.eq(challenge))
                .leftJoin(exerciseAnalysis)
                .on(exerciseAnalysis.user.eq(userParam),
                        exerciseAnalysis.date.goe(challengeStatus.createdAt),
                        exerciseAnalysis.date.goe(challenge.startAt),
                        exerciseAnalysis.date.loe(challenge.endAt))
                .where(challengeStatus.user.eq(userParam))
                .groupBy(exerciseAnalysis.user, challenge.id)
                .fetch();
    }

    @Override
    public List<ChallengeDetailsForTeacherVO> queryChallengeProgress(
            Challenge challengeParam,
            String name,
            ChallengeParticipantsScope participantsScope,
            ChallengeParticipantsOrder participantsOrder,
            Integer grade,
            Integer classNum,
            Long page
    ) {
        return queryFactory
                .select(new QChallengeDetailsForTeacherVO(
                        user.id,
                        user.name,
                        section.grade,
                        section.classNum,
                        user.number,
                        school.name,
                        user.profileImageUrl,
                        getChallengeTotalValue(challengeParam),
                        getChallengeProgress(challengeParam),
                        exerciseAnalysis.date.count().goe(challengeParam.getSuccessStandard()),
                        new CaseBuilder()
                                .when(exerciseAnalysis.date.count().goe(challengeParam.getSuccessStandard()))
                                .then(exerciseAnalysis.date.max())
                                .otherwise(Expressions.nullExpression())))
                .from(user)
                .offset(page * PARTICIPANTS_SIZE)
                .limit(PARTICIPANTS_SIZE)
                .leftJoin(user.section, section)
                .join(user.school, school)
                .join(user.challengeStatuses, challengeStatus)
                .join(challengeStatus.challenge, challenge)
                .leftJoin(user.exerciseAnalyses, exerciseAnalysis)
                .on(challengeDateFilter(challengeParam),
                        isChallengeSuccessFilter(challengeParam))
                .where(challenge.eq(challengeParam),
                        userScopeFilter(participantsScope),
                        userNameContainsFilter(name),
                        userGradeFilter(grade),
                        userClassNumFilter(classNum))
                .orderBy(challengeParticipantsOrder(participantsOrder))
                .groupBy(user.id, challengeStatus.createdAt)
                .fetch();
    }

    @Override
    public List<ChallengeDetailsForTeacherVO> queryChallengeProgress(
            Challenge challengeParam,
            String name,
            ChallengeParticipantsScope participantsScope,
            ChallengeParticipantsOrder participantsOrder,
            Integer grade,
            Integer classNum
    ) {
        return queryFactory
                .select(new QChallengeDetailsForTeacherVO(
                        user.id,
                        user.name,
                        section.grade,
                        section.classNum,
                        user.number,
                        school.name,
                        user.profileImageUrl,
                        getChallengeTotalValue(challengeParam),
                        getChallengeProgress(challengeParam),
                        exerciseAnalysis.date.count().goe(challengeParam.getSuccessStandard()),
                        new CaseBuilder()
                                .when(exerciseAnalysis.date.count().goe(challengeParam.getSuccessStandard()))
                                .then(exerciseAnalysis.date.max())
                                .otherwise(Expressions.nullExpression())))
                .from(user)
                .leftJoin(user.section, section)
                .join(user.school, school)
                .join(user.challengeStatuses, challengeStatus)
                .join(challengeStatus.challenge, challenge)
                .on(challenge.eq(challengeParam))
                .leftJoin(user.exerciseAnalyses, exerciseAnalysis)
                .on(challengeDateFilter(challengeParam),
                        isChallengeSuccessFilter(challengeParam))
                .where(userScopeFilter(participantsScope),
                        userNameContainsFilter(name),
                        userGradeFilter(grade),
                        userClassNumFilter(classNum))
                .orderBy(challengeParticipantsOrder(participantsOrder))
                .groupBy(user.id, challengeStatus.createdAt, exerciseAnalysis.user)
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

    private BooleanExpression challengeDateFilter(Challenge challenge) {
        return exerciseAnalysis.date.goe(challenge.getStartAt())
                .and(exerciseAnalysis.date.goe(challengeStatus.createdAt))
                .and(exerciseAnalysis.date.loe(challenge.getEndAt()));
    }

    private BooleanExpression userNameContainsFilter(String keyword) {
        return keyword != null ? user.name.contains(keyword) : null;
    }

    private BooleanExpression userGradeFilter(Integer grade) {
        return grade != null ? user.section.grade.eq(grade) : null;
    }

    private BooleanExpression userClassNumFilter(Integer classNum) {
        return classNum != null ? user.section.classNum.eq(classNum) : null;
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

    private Expression<Integer> getChallengeTotalValue(Challenge challenge) {
        NumberExpression<Integer> exerciseAmount;

        if (challenge.getGoalType() == GoalType.WALK) {
            exerciseAmount = exerciseAnalysis.walkCount;
        } else {
            exerciseAmount = exerciseAnalysis.distance;
        }

        return ExpressionUtils.as(
                JPAExpressions.select(exerciseAmount.sum())
                        .from(exerciseAnalysis)
                        .where(exerciseAnalysis.user.eq(user),
                                challengeDateFilter(challenge)),
                "totalValue"
        );
    }

    private Expression<Integer> getChallengeProgress(Challenge challenge) {
        NumberExpression<Integer> sum;

        if (challenge.getGoalScope() == GoalScope.ALL) {
            if (challenge.getGoalType() == GoalType.DISTANCE) {
                sum = exerciseAnalysis.distance.sum();
            } else {
                sum = exerciseAnalysis.walkCount.sum();
            }
            return ExpressionUtils.as(
                    JPAExpressions.select(sum.divide(challenge.getGoal()).multiply(100).round())
                            .from(exerciseAnalysis)
                            .where(exerciseAnalysis.user.eq(user),
                                    challengeDateFilter(challenge)),
                    "progress"
            );
        } else {
            return exerciseAnalysis.date.count().intValue().divide(challenge.getSuccessStandard()).multiply(100).round();
        }
    }

    private OrderSpecifier<?> challengeParticipantsOrder(ChallengeParticipantsOrder challengeParticipantsOrder) {
        switch (challengeParticipantsOrder) {
            case SUCCESS_DATE: {
                return new OrderSpecifier<>(Order.DESC, exerciseAnalysis.date.max());
            }
            case PROGRESS: {
                return new OrderSpecifier<>(Order.DESC,
                        exerciseAnalysis.walkCount.sum().divide(exerciseAnalysis.date.count()));
            }
            case SCHOOL_NAME: {
                return new OrderSpecifier<>(Order.ASC, school.name);
            }
            case USER_NAME: {
                return new OrderSpecifier<>(Order.ASC, user.name);
            }
            default: {
                return null;
            }
        }
    }
}
