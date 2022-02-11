package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
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
        private Long userId;
        private String name;
        private String profileImageUrl;
        private Integer grade;
        private Integer classNum;
        private Integer number;
        private Boolean isTeacher;

        @QueryProjection
        public UserListInfo(Long userId, String name, String profileImageUrl, Integer grade, Integer classNum, Integer number, Boolean isTeacher) {
            this.userId = userId;
            this.name = name;
            this.profileImageUrl = profileImageUrl;
            this.grade = grade;
            this.classNum = classNum;
            this.number = number;
            this.isTeacher = isTeacher;
        }
    }
}
