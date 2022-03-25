package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.exercise.vo.ExerciseVO;

import java.time.ZonedDateTime;
import java.util.List;

public interface ExerciseRepositoryCustom {
    List<ExerciseVO> queryExerciseHistoryList(Long userId, ZonedDateTime startAt, ZonedDateTime endAt, Integer page);
}
