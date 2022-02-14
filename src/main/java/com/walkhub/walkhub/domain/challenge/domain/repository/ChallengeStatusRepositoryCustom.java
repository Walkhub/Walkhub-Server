package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.repository.vo.RelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForStudentResponse;
import com.walkhub.walkhub.domain.school.domain.School;

import java.util.List;

public interface ChallengeStatusRepositoryCustom {
    Integer getParticipantsListByChallengeId(Long challengeId);
    List<RelatedChallengeParticipantsVO> getRelatedChallengeParticipantsList(Long challengeId, School school, Integer grade, Integer classNum);
}
