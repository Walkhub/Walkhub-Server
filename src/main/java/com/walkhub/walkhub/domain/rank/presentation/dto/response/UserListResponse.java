package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserListResponse {

    private final List<UserSearchResponse> userList;

    @Getter
    @Builder
    public static class UserSearchResponse {
        private final Long userId;
        private final String name;
        private final Integer ranking;
        private final Integer grade;
        private final Integer classNum;
        private final String profileImageUrl;
        private final Integer walkCount;
    }
}
