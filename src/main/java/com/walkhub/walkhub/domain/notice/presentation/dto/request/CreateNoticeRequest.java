package com.walkhub.walkhub.domain.notice.presentation.dto.request;

import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateNoticeRequest {

    @NotBlank(message = "title은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String title;

    @NotBlank(message = "content는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String content;

    @NotNull(message = "scope는 Null, 공백을 허용하지 않습니다.")
    private Scope scope;
}
