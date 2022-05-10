package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.exercise.vo.ExerciseVO;
import org.springframework.data.domain.Page;

import java.time.ZonedDateTime;

public interface ExerciseRepositoryCustom {
    Page<ExerciseVO> queryExerciseHistoryList(Long userId, ZonedDateTime startAt, ZonedDateTime endAt, Integer page);
}
