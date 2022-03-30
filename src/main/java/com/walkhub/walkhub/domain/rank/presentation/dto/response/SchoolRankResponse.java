package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchoolRankResponse {

    private final MySchoolResponse mySchoolRank;

    @Getter
    @Builder
    public static class MySchoolResponse {
        private final Long schoolId;
        private final String name;
        private final String logoImageUrl;
        private final Integer grade;
        private final Integer classNum;
    }
}
