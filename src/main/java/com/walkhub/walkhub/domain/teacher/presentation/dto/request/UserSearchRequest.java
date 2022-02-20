package com.walkhub.walkhub.domain.teacher.presentation.dto.request;

import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import lombok.Data;

@Data
public class UserSearchRequest {
    private AuthorityScope scope;
    private SortStandard sort;
    private Integer grade;
    private Integer classNum;
    private String name;
}
