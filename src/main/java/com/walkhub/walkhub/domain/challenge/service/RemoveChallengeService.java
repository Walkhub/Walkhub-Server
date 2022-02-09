package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RemoveChallengeService {

    private final ChallengeFacade challengeFacade;
    private final ChallengeRepository challengeRepository;

    public void execute(Long id) {
        Challenge challenge = challengeFacade.getByChallengeId(id);
        challengeRepository.delete(challenge);
    }
}
