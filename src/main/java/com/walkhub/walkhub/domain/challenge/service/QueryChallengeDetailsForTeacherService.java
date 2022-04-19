package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeDetailsForTeacherService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;

    public QueryChallengeDetailsForTeacherResponse execute(Long challengeId) {
        User user = userFacade.getCurrentUser();
        Challenge challenge = challengeFacade.getChallengeById(challengeId);

        return challengeFacade.builderChallenge(user, challenge);
    }
}
