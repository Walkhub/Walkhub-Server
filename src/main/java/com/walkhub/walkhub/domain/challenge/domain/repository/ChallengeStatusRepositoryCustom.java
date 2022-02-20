package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeProgressVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.RelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeVO;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsOrder;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsScope;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.User;

import java.util.List;

public interface ChallengeStatusRepositoryCustom {
    Integer getParticipantsCountByChallengeId(Long challengeId);
    List<RelatedChallengeParticipantsVO> getRelatedChallengeParticipantsList(Long challengeId, School school, Integer grade, Integer classNum);
    List<ChallengeParticipantsVO> queryChallengeParticipantsList(Challenge challenge, SuccessScope successScope, Long page);
    void deleteNotOverChallengeStatusByUserId(Long userId);
    void resignParticipatedChallenge(User user);
    List<ShowChallengeVO> getAllChallengesByUser(User user);
    List<ChallengeProgressVO> queryChallengeProgress(Challenge challenge, ChallengeParticipantsScope participantsScope, ChallengeParticipantsOrder participantsOrder, SuccessScope successScope, Long page);
}
