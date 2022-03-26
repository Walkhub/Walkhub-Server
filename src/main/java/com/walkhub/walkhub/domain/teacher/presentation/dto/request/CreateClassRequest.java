package com.walkhub.walkhub.domain.teacher.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateClassRequest {

    @NotNull(message = "grade는 null을 허용하지 않습니다.")
    private Integer grade;

    @NotNull(message = "class_num은 null을 허용하지 않습니다.")
    private Integer classNum;

}
