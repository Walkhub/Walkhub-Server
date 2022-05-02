package com.walkhub.walkhub.domain.teacher.presentation.dto.request;

import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserSearchRequest {

    @NotNull(message = "scope는 Null일 수 없습니다.")
    private final AuthorityScope scope;

    @NotNull(message = "sort는 Null일 수 없습니다.")
    private final SortStandard sort;

    private final Integer grade;

    private final Integer classNum;

    private final String name;

}
