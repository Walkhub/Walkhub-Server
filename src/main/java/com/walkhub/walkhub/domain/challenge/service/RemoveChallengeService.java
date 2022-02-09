package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.exception.NotYourChallengeException;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RemoveChallengeService {

    private final ChallengeFacade challengeFacade;
    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;

    public void execute(Long id) {
        User user = userFacade.getCurrentUser();
        Challenge challenge = challengeFacade.getByChallengeId(id);

        if (!challenge.isWriter(user.getId()))
            throw NotYourChallengeException.EXCEPTION;

        challengeRepository.delete(challenge);
    }
}