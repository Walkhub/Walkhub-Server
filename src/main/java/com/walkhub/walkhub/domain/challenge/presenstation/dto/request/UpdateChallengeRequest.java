package com.walkhub.walkhub.domain.challenge.presenstation.dto.request;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UpdateChallengeRequest {

    @NotBlank(message = "name은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String name;

    @NotBlank(message = "content는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String content;

    private String imageUrl;

    @NotNull(message = "start_at은 Null일 수 없습니다.")
    @FutureOrPresent(message = "start_at은 과거일 수 없습니다.")
    private LocalDate startAt;

    @NotNull(message = "end_at은 Null일 수 없습니다.")
    @FutureOrPresent(message = "end_at은 과거일 수 없습니다.")
    private LocalDate endAt;

    @NotBlank(message = "award는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String award;

    @NotNull(message = "goal은 Null일 수 없습니다.")
    private Integer goal;

    @NotNull(message = "goal_type은 Null일 수 없습니다")
    private GoalType goalType;

    @NotNull(message = "goal_scope는 Null일 수 없습니다")
    private GoalScope goalScope;

    @NotNull(message = "success_standard는 Null일 수 없습니다")
    private Integer successStandard;
}
