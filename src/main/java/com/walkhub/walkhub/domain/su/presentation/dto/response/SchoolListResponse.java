package com.walkhub.walkhub.domain.su.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class SchoolListResponse {

    private final List<SchoolResponse> schoolList;

    @Getter
    @Builder
    public static class SchoolResponse {
        private final Long schoolId;
        private final String name;
        private final String logoImageUrl;
        private final Long userCount;
    }
}
