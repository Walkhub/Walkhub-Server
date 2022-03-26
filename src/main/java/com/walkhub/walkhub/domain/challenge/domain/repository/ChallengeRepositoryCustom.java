package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForStudentVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeVO;
import com.walkhub.walkhub.domain.user.domain.User;

import java.util.List;

public interface ChallengeRepositoryCustom {
    List<ShowChallengeVO> queryChallenge(User user);

    ChallengeDetailsForStudentVO queryChallengeDetailsForStudent(Challenge challenge, User user);
}
