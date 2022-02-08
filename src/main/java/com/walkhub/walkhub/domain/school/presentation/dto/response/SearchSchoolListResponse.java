package com.walkhub.walkhub.domain.school.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchSchoolListResponse {

    public final List<SearchSchoolResponse> searchSchoolList;


    @Getter
    @Builder
    public static class SearchSchoolResponse {
        private final Long schoolId;
        private final String schoolName;
        private final String logoImageUrl;
    }

}
