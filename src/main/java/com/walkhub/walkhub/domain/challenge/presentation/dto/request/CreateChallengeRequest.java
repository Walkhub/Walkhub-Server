package com.walkhub.walkhub.domain.challenge.presentation.dto.request;

import com.walkhub.walkhub.global.enums.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateChallengeRequest {

    @NotBlank(message = "name은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 200, message = "name은 200글자 미만이어야 합니다.")
    private String name;

    @NotBlank(message = "content는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String content;

    @NotBlank(message = "created_at은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private LocalDateTime createdAt;

    @NotBlank(message = "end_at은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private LocalDateTime endAt;

    @NotBlank(message = "goal은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private Long goal;

    @NotBlank(message = "award는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 200, message = "award는 200글자 미만이여야 합니다.")
    private String award;

    @NotBlank(message = "scope는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private Scope scope;

}
