package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.QueryExerciseHistoryRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseHistoryResponse.ExerciseHistory;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QQueryExerciseHistoryResponse_ExerciseHistory;
import lombok.RequiredArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.walkhub.walkhub.domain.exercise.domain.QExercise.exercise;

@RequiredArgsConstructor
public class ExerciseRepositoryCustomImpl implements ExerciseRepositoryCustom {

    private static final int PARTICIPANTS_SIZE = 4;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ExerciseHistory> queryExerciseHistoryList(Long userId, QueryExerciseHistoryRequest request) {

        ZonedDateTime startAt = request.getStartAt().atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endAt = request.getEndAt().atStartOfDay(ZoneId.systemDefault());
        Integer page = request.getPage();

        return queryFactory
                .select(
                        new QQueryExerciseHistoryResponse_ExerciseHistory(
                                exercise.id,
                                exercise.imageUrl,
                                exercise.walkCount,
                                null,
                                exercise.calorie,
                                null,
                                null,
                                null,
                                exercise.endAt
                        )
                )
                .from(exercise)
                .offset(page == null ? 0 : page * PARTICIPANTS_SIZE)
                .limit(PARTICIPANTS_SIZE)
                .where(
                        exercise.createdAt.between(startAt, endAt),
                        exercise.user.id.eq(userId)
                )
                .fetch();
    }

}
