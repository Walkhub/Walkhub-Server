package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForStudentVO;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForStudentResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeDetailsForStudentService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;
    private final ChallengeRepository challengeRepository;

    public QueryChallengeDetailsForStudentResponse execute(Long challengeId) {
        Challenge challenge = challengeFacade.getChallengeById(challengeId);
        User user = userFacade.getCurrentUser();

        ChallengeDetailsForStudentVO challengeDetailsForStudentVO = challengeRepository.queryChallengeDetails(challenge, user);
    }
}