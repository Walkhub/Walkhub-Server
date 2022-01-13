package com.walkhub.walkhub.domain.challenge.facade;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChallengeFacade {

    private final ChallengeRepository challengeRepository;

    public Challenge getById(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow();
    }

}
