package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class DetailsClassResponse {

    private final Integer grade;
    private final Integer classNum;
    private final String classCode;
    private final TeacherResponse teacher;

    @Getter
    @Builder
    public static class TeacherResponse {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;
    }

}
