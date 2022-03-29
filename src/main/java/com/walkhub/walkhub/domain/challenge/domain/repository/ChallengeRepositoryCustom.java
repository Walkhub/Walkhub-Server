package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForStudentVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.RelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeVO;
import com.walkhub.walkhub.domain.user.domain.User;

import java.util.List;

public interface ChallengeRepositoryCustom {
    List<ShowChallengeVO> queryChallengeListForStudent(User user);

    ChallengeDetailsForStudentVO queryChallengeDetailsForStudent(Challenge challenge, User user);

    List<RelatedChallengeParticipantsVO> getRelatedChallengeParticipantsList(Long challengeId, User user);
}
