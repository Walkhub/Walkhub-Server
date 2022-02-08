package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateSchoolInfoRequest {

    @NotNull(message = "school_id는 Null일 수 없습니다.")
    private Long schoolId;
}
