package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.exercise.vo.ExerciseVO;
import com.walkhub.walkhub.domain.exercise.vo.QExerciseVO;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

import static com.walkhub.walkhub.domain.exercise.domain.QExercise.exercise;
import static com.walkhub.walkhub.domain.exercise.domain.QLocation.location;

@RequiredArgsConstructor
public class ExerciseRepositoryCustomImpl implements ExerciseRepositoryCustom {

    private static final int PARTICIPANTS_SIZE = 4;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ExerciseVO> queryExerciseHistoryList(Long userId, ZonedDateTime startAt, ZonedDateTime endAt, Integer page) {

        return queryFactory
                .select(
                        new QExerciseVO(
                                exercise.id,
                                exercise.imageUrl,
                                exercise.walkCount,
                                exercise.distance.castToNum(Double.TYPE)
                                        .divide(differenceSec(exercise.createdAt, exercise.endAt)
                                                .subtract(exercise.pausedTime)),
                                exercise.calorie,
                                differenceMinute(exercise.createdAt, exercise.endAt)
                                        .subtract(exercise.pausedTime),
                                location.latitude,
                                location.longitude,
                                exercise.endAt
                        )
                )
                .from(exercise)
                .join(location.exercise, exercise)
                .on(location.sequence.eq(location.sequence.max()))
                .offset(page == null ? 0 : page * PARTICIPANTS_SIZE)
                .limit(PARTICIPANTS_SIZE)
                .where(
                        exercise.createdAt.between(startAt, endAt),
                        exercise.user.id.eq(userId)
                )
                .fetch();
    }

    private NumberExpression<Integer> differenceMinute(DateTimePath<?> start, DateTimePath<?> end) {
        return Expressions.asNumber(start.minute())
                .subtract(Expressions.asNumber(end.minute()));
    }

    private NumberExpression<Double> differenceSec(DateTimePath<?> start, DateTimePath<?> end) {
        return differenceMinute(start, end)
                .multiply(60).castToNum(Double.TYPE);
    }

}
