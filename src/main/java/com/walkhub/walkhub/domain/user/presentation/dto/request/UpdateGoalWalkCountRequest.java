package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateGoalWalkCountRequest {

    @NotNull(message = "walk_count null일 수 없습니다.")
    @Size(min = 1, max = 50000 ,message = "walk_count는 1이상 500000 이하 여야 합니다" )
    private Integer walkCount;
}
