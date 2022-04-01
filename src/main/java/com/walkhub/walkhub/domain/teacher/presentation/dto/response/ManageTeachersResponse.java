package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ManageTeachersResponse {
    private final String authCode;
    private final List<ClassResponse> teacherList;
}
