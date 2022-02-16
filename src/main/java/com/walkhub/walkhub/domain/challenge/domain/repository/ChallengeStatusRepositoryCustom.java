package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.repository.vo.RelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;

import java.util.List;

public interface ChallengeStatusRepositoryCustom {
    Integer getParticipantsCountByChallengeId(Long challengeId);
    List<RelatedChallengeParticipantsVO> getRelatedChallengeParticipantsList(Long challengeId, School school, Integer grade, Integer classNum);
    List<ChallengeParticipantsVO> queryChallengeParticipantsList(Challenge challenge, SuccessScope successScope, Long page);
    void deleteNotOverChallengeStatusByUserId(Long userId);
}
