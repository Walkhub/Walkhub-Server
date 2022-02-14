package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryUserListResponse {

    private final List<UserListInfo> userList;

    @Getter
    @Builder
    public static class UserListInfo {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;
        private final Integer grade;
        private final Integer classNum;
        private final Integer number;
        private final Boolean isTeacher;
    }
}
