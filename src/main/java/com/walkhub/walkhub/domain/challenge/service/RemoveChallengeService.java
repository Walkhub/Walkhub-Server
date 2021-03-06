package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class RemoveChallengeService {

    private final ChallengeFacade challengeFacade;
    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;

    @Transactional
    public void execute(Long id) {
        User user = userFacade.getCurrentUser();
        Challenge challenge = challengeFacade.getChallengeById(id);

        if (!challenge.isWriter(user.getId()))
            throw InvalidRoleException.EXCEPTION;

        challengeRepository.delete(challenge);
    }
}