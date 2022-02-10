package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateGoalWalkCountRequest {

    @NotNull(message = "daily_walk_count_goal null일 수 없습니다.")
    @Size(min = 6000, max = 99999, message = "daily_walk_count_goal는 6000이상 99999 이하 여야 합니다")
    private Integer dailyWalkCountGoal;
}