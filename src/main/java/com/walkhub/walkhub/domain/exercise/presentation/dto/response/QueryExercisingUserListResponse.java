package com.walkhub.walkhub.domain.exercise.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryExercisingUserListResponse {

    private final List<ExercisingUserListResponse> userList;

    @Getter
    @Builder
    public static class ExercisingUserListResponse {
        private final Long userId;
        private final String name;
        private final String profileImageUrl;
    }
}
