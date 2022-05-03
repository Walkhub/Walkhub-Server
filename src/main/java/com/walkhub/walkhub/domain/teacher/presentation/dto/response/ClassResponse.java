package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassResponse {

    private final SectionResponse section;
    private final TeacherResponse teacher;

    @Getter
    @Builder
    public static class SectionResponse {
        private final Long sectionId;
        private final Integer grade;
        private final Integer classNum;
        private final Integer userCount;
    }

    @Getter
    @Builder
    public static class TeacherResponse {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;
    }

}
