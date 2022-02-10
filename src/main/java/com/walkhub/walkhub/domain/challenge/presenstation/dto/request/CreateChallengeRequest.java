package com.walkhub.walkhub.domain.challenge.presenstation.dto.request;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateChallengeRequest {
    @NotBlank(message = "name은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String name;

    @NotBlank(message = "name은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String content;

    private String imageUrl;

    @NotNull(message = "start_at은 Null일 수 없습니다.")
    private LocalDateTime startAt;

    @NotNull(message = "end_at은 Null일 수 없습니다.")
    private LocalDateTime endAt;

    @NotBlank(message = "award는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String award;

    @NotNull(message = "user_scope은 Null일 수 없습니다.")
    private UserScope userScope;

    @NotNull(message = "goal은 Null일 수 없습니다.")
    private Integer goal;

    @NotNull(message = "goal_type은 Null일 수 없습니다")
    private GoalType goalType;

    @NotNull(message = "goal_scope는 Null일 수 없습니다")
    private GoalScope goalScope;

    @NotNull(message = "success_standard는 Null일 수 없습니다")
    private Integer successStandard;
}
