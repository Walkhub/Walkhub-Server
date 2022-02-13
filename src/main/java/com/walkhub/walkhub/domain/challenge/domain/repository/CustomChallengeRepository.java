package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;

import java.util.List;

public interface CustomChallengeRepository {
    List<ChallengeParticipantsVO> queryChallengeParticipantsList(Long challengeId, SuccessScope successScope);
}
