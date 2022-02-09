package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class DetailsClassResponse {

    private final String classCode;
    private final List<UserListResponse> userList;


    @Getter
    @Builder
    public static class UserListResponse {
        private Long userId;
        private String name;
        private String profileImageUrl;
        private Integer walkCount;
    }
}
