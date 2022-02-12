package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;

@RequiredArgsConstructor
public class CustomExerciseRepositoryImpl /*implements CustomExerciseRepository*/ {

    private final JPAQueryFactory jpaQueryFactory;

    // 해당 챌린지에 대해 성공한 날짜
//    @Override
//    public List<LocalDate> querySuccessDateList(ChallengeStatus challengeStatus) {
//        return jpaQueryFactory
//                .select(exerciseAnalysis.date)
//                .from(exerciseAnalysis)
//                .where(
//                        exerciseAnalysis.user.eq(challengeStatus.getUser()),
//                        challengeGoalTypeFilter(challengeStatus)
//                )
//                .fetch();
//    }
//
//    // 챌린지 목표 타입에 따른 필터링
//    private BooleanExpression challengeGoalTypeFilter(ChallengeStatus challengeStatus) {
//        GoalScope goalScope = challengeStatus.getChallenge().getGoalScope();
//        switch (goalScope) {
//            case ALL: {
//                return null;
//            }
//            case DAY: {
//                return exerciseAnalysis.walkCount.gt(challengeStatus.getChallenge().getGoal());
//            }
//            default:
//                return null;
//        }
//    }

}
