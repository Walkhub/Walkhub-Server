package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;

@RequiredArgsConstructor
public class CustomExerciseRepositoryImpl implements CustomExerciseRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LocalDate> querySuccessDateList(ChallengeStatus challengeStatus) {
        return jpaQueryFactory
                .select(exerciseAnalysis.date)
                .from(exerciseAnalysis)
                .where(
                        exerciseAnalysis.user.eq(challengeStatus.getUser()),
                        exerciseAnalysis.walkCount.gt(challengeStatus.getChallenge().getGoal())
                )
                .fetch();
    }

}
