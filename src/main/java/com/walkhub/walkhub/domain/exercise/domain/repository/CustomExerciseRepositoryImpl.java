package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class CustomExerciseRepositoryImpl implements CustomExerciseRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LocalDate> querySuccessDateList(Long userId, Challenge challenge) {
        LocalDate startAt = challenge.getStartAt().toLocalDate();
        LocalDate endAt = challenge.getEndAt().toLocalDate();

        return jpaQueryFactory
                .select(exerciseAnalysis.date)
                .from(exerciseAnalysis)
                .join(user)
                .on(exerciseAnalysis.user.id.eq(userId))
                .where(
                        exerciseAnalysis.date.between(startAt, endAt),
                        challengeGoalTypeFilter(challenge)
                )
                .fetch();
    }

    private BooleanExpression challengeGoalTypeFilter(Challenge challenge) { // 수정 필요
        if (challenge.getGoalType() == GoalType.WALK) {
            return challengeGoalScopeFilter(challenge);
        }

        return exerciseAnalysis.distance.gt(challenge.getGoal());
    }

    private BooleanExpression challengeGoalScopeFilter(Challenge challenge) { // 수정 필요
        GoalScope goalScope = challenge.getGoalScope();

        if (goalScope == GoalScope.ALL) {
            // 모든 걸음 수 합으로 수정 필요
            return null;
        }
        return exerciseAnalysis.walkCount.gt(challenge.getGoal());
    }

}
