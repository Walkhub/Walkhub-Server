package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForTeacherVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowParticipatedChallengeVO;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsOrder;
import com.walkhub.walkhub.domain.challenge.domain.type.ChallengeParticipantsScope;
import com.walkhub.walkhub.domain.user.domain.User;

import java.util.List;

public interface ChallengeStatusRepositoryCustom {
    List<ChallengeDetailsForTeacherVO> queryChallengeProgress(Challenge challenge,
                                                              String name,
                                                              ChallengeParticipantsScope participantsScope,
                                                              ChallengeParticipantsOrder participantsOrder,
                                                              Integer grade,
                                                              Integer classNum,
                                                              Long page);

    List<ShowParticipatedChallengeVO> getParticipatedChallengesByUser(User user);

    void deleteNotOverChallengeStatusByUserId(Long userId);
}
