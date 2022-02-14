package com.walkhub.walkhub.domain.challenge.facade;


import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.exception.ChallengeNotFoundException;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.Writer;
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

    public ChallengeResponse challengeResponseBuilder(Challenge challenge) {
        return ChallengeResponse.builder()
            .id(challenge.getId())
            .name(challenge.getName())
            .startAt(challenge.getStartAt())
            .endAt(challenge.getEndAt())
            .imageUrl(challenge.getImageUrl())
            .userScope(challenge.getUserScope())
            .goalScope(challenge.getGoalScope())
            .goalType(challenge.getGoalType())
            .writer(Writer.builder()
                .userId(challenge.getUser().getId())
                .name(challenge.getUser().getName())
                .profileImageUrl(challenge.getUser().getProfileImageUrl())
                .build())
            .build();
    }
}
