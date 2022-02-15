package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class DetailsClassResponse {

    private final String classCode;
    private final TeacherResponse teacher;
    private final List<UserListResponse> userList;

    @Getter
    @Builder
    public static class TeacherResponse {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;
    }

    @Getter
    @Builder
    public static class UserListResponse {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;
        private final Integer walkCount;
    }
}
