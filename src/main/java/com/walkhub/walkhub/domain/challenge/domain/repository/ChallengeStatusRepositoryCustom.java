package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForStudentResponse;

import java.util.List;

public interface ChallengeStatusRepositoryCustom {
    Integer getParticipantsListByChallengeId(Long challengeId);
    List<QueryChallengeParticipantsForStudentResponse.RelatedChallengeParticipants> getRelatedChallengeParticipantsList(Long challengeId);
}
