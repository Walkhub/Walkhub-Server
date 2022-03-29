package com.walkhub.walkhub.domain.challenge.facade;


import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.exception.ChallengeNotFoundException;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChallengeFacade {

    private final ChallengeRepository challengeRepository;

    public Challenge getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);
    }

    public PersonResponse personBuilder(Long userId, String name, String profileUrl) {
        return PersonResponse.builder()
                .userId(userId)
                .name(name)
                .profileImageUrl(profileUrl)
                .build();
    }
}
