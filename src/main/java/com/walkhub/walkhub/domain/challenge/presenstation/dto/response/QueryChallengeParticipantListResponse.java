package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryChallengeParticipantListResponse {

    private final Integer totalPage;
    private final List<QueryChallengeParticipantResponse> participantList;

    @Getter
    @Builder
    public static class QueryChallengeParticipantResponse {
        private final Long userId;
        private final String name;
        private final Integer grade;
        private final Integer classNum;
        private final Integer number;
        private final String schoolName;
        private final String profileImageUrl;
        private final Integer totalWalkCount;
        private final Integer progress;
        private final Boolean isSuccess;
        private final LocalDate successDate;
    }

}
