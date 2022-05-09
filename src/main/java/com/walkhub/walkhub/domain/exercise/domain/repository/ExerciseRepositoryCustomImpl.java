package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.exercise.vo.ExerciseVO;
import com.walkhub.walkhub.domain.exercise.vo.QExerciseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.ZonedDateTime;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.walkhub.walkhub.domain.exercise.domain.QExercise.exercise;
import static com.walkhub.walkhub.domain.exercise.domain.QLocation.location;

@RequiredArgsConstructor
public class ExerciseRepositoryCustomImpl implements ExerciseRepositoryCustom {

    private static final int PARTICIPANTS_SIZE = 4;

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ExerciseVO> queryExerciseHistoryList(Long userId, ZonedDateTime startAt, ZonedDateTime endAt, Integer page) {
        List<ExerciseVO> results = queryFactory
                .select(
                        new QExerciseVO(
                                exercise.id,
                                exercise.imageUrl,
                                exercise.walkCount,
                                exercise.distance.divide(100).castToNum(Double.TYPE)
                                        .divide(getExerciseTime(differenceSec(exercise.endAt, exercise.createdAt)))
                                        .coalesce((double) 0),
                                exercise.calorie,
                                getExerciseTime(differenceMinute(exercise.endAt, exercise.createdAt)),
                                location.latitude,
                                location.longitude,
                                exercise.endAt
                        )
                )
                .from(exercise)
                .leftJoin(exercise.locations, location)
                .on(
                        Expressions.asNumber(
                                select(location.sequence.max())
                                        .from(location)
                                        .where(location.exercise.eq(exercise))
                        ).eq(location.sequence)
                )
                .offset((long) page * PARTICIPANTS_SIZE)
                .limit(PARTICIPANTS_SIZE)
                .where(
                        exercise.createdAt.between(startAt, endAt),
                        exercise.endAt.isNotNull(),
                        exercise.user.id.eq(userId)
                )
                .fetch();

        return PageableExecutionUtils.getPage(results, Pageable.unpaged(), results::size);
    }

    private NumberExpression<Integer> differenceSec(DateTimePath<?> start, DateTimePath<?> end) {
        return Expressions.numberTemplate(Integer.class, "timestampdiff(second, {0}, {1})", end, start);
    }

    private NumberExpression<Integer> differenceMinute(DateTimePath<?> start, DateTimePath<?> end) {
        return Expressions.numberTemplate(Integer.class, "timestampdiff(minute, {0}, {1})", end, start);
    }

    private NumberExpression<Integer> getExerciseTime(NumberExpression<Integer> difference) {
        return difference.subtract(exercise.pausedTime);
    }

}
