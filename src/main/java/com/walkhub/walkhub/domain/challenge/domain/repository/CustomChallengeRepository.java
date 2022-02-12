package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForTeacherResponse;

import java.util.List;

public interface CustomChallengeRepository {
    List<QueryChallengeParticipantsForTeacherResponse.ChallengeParticipants> queryChallengeParticipantsList(Challenge challenge, SuccessScope successScope);
}
