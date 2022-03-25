package com.walkhub.walkhub.domain.teacher.presentation.dto.request;

import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSearchRequest {
    private final AuthorityScope scope;
    private final SortStandard sort;
    private final Integer grade;
    private final Integer classNum;
    private final String name;
}
