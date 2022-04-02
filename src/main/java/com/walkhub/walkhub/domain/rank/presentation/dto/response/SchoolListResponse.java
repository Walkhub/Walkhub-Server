package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SchoolListResponse {

    private final List<SchoolResponse> schoolList;

    @Getter
    @Builder
    public static class SchoolResponse {
        private final Long schoolId;
        private final String schoolName;
        private final Integer ranking;
        private final String logoImageUrl;
        private final Integer walkCount;
        private final Long userCount;

        @QueryProjection
        public SchoolResponse(Long schoolId, String schoolName, Integer ranking,
            String logoImageUrl, Integer walkCount, Long userCount) {
            this.schoolId = schoolId;
            this.schoolName = schoolName;
            this.ranking = ranking;
            this.logoImageUrl = logoImageUrl;
            this.walkCount = walkCount;
            this.userCount = userCount;
        }
    }
}
