package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClassListResponse {

    private final String authCode;
    private final List<ClassResponse> classList;

    @Getter
    @Builder
    public static class ClassResponse {
        private final Integer userCount;
        private final SectionResponse section;
        private final TeacherResponse teacher;
    }

    @Getter
    @Builder
    public static class SectionResponse {
        private final Long sectionId;
        private final Integer grade;
        private final Integer classNum;
    }

    @Getter
    @Builder
    public static class TeacherResponse {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;
    }

}
