package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserListResponse {

    private final List<UserSearchResponse> userList;

    @Getter
    @Builder
    public static class UserSearchResponse {
        private final String accountId;
        private final String name;
        private final Integer rank;
        private final Integer grade;
        private final Integer classNum;
        private final String profileImageUrl;
        private final Integer walkCount;
    }
}
