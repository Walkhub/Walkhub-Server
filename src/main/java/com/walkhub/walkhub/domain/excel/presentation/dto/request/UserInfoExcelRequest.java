package com.walkhub.walkhub.domain.excel.presentation.dto.request;

import com.walkhub.walkhub.domain.excel.domain.type.UserType;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserInfoExcelRequest {

    @NotNull(message = "sort는 Null일 수 없습니다.")
    private final SortStandard sort;

    @NotNull(message = "user_type은 Null일 수 없습니다.")
    private final UserType userType;

    private final Integer grade;

    private final Integer classNum;

}
