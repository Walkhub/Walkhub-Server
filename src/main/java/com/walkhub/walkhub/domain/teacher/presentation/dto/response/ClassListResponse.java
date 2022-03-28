package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClassListResponse {

    private final List<ClassResponse> teacherList;

}
