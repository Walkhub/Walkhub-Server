package com.walkhub.walkhub.domain.notice.presentation.dto.request;

import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class CreateNoticeRequest {

    @NotBlank(message = "title은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 200, message = "title은 200글자이상을 허용하지 않습니다.")
    private String title;

    @NotBlank(message = "content는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String content;

    @NotNull(message = "scope는 Null, 공백을 허용하지 않습니다.")
    private Scope scope;
}
