package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForStudentVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.RelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeListForTeacherVo;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeVO;
import com.walkhub.walkhub.domain.user.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface ChallengeRepositoryCustom {
    List<ShowChallengeVO> queryChallengeListForStudent(User user);

    List<ShowChallengeListForTeacherVo> queryChallengeListForTeacher(User user, LocalDate date);

    ChallengeDetailsForStudentVO queryChallengeDetailsForStudent(Challenge challenge, User user);

    List<RelatedChallengeParticipantsVO> getRelatedChallengeParticipantsList(Long challengeId, User user);
}