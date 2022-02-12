package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QQueryChallengeParticipantsForTeacherResponse_ChallengeParticipants;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForTeacherResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.challenge.domain.QChallenge.challenge;
import static com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus.challengeStatus;
import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class CustomChallengeRepositoryImpl implements CustomChallengeRepository {

    private final JPAQueryFactory jpaQueryFactory;

    // dto로 조회
    @Override
    public List<QueryChallengeParticipantsForTeacherResponse.ChallengeParticipants> queryChallengeParticipantsList(Challenge challenge, SuccessScope successScope) {
        return jpaQueryFactory
                .select(new QQueryChallengeParticipantsForTeacherResponse_ChallengeParticipants(
                        user.id.as("userId"),
                        user.section.grade,
                        user.section.classNum,
                        user.number,
                        user.name,
                        user.profileImageUrl,
                        user.school.name.as("schoolName"),
                        ExpressionUtils.list(
                                LocalDate.class,
                                JPAExpressions
                                        .select(exerciseAnalysis.date)
                                        .from(exerciseAnalysis)
                                        .where(
                                                exerciseAnalysis.user.eq(challengeStatus.user),
                                                challengeGoalTypeFilter(challenge)
                                        )

                        ),
                        challengeStatus.successCount.gt(challenge.getSuccessStandard())
                ))
                .from(user)
                .join(exerciseAnalysis)
                .on(exerciseAnalysis.user.eq(user))
                .join(challengeStatus)
                .on(
                        challengeStatus.challenge.eq(challenge),
                        challengeStatus.user.eq(user)
                )
                .where(successScopeFilter(successScope))
                .fetch();
    }

    // 성공 스코프에 따른 필터링
    private BooleanExpression successScopeFilter(SuccessScope successScope) {
        switch (successScope) {
            case TRUE: {
                return challenge.successStandard.lt(challengeStatus.successCount);
            }
            case FALSE: {
                return challenge.successStandard.gt(challengeStatus.successCount);
            }
            default:
                return null;
        }
    }

    // 목표 타입에 따른 필터링
    private BooleanExpression challengeGoalTypeFilter(Challenge challenge) {
        GoalScope goalScope = challenge.getGoalScope();
        switch (goalScope) {
            case ALL: {
                return null; // 수정
            }
            case DAY: {
                return exerciseAnalysis.walkCount.gt(challenge.getGoal());
            }
            default:
                return null;
        }
    }

}
