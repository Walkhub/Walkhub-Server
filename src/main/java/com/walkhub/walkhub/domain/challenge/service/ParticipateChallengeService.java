package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ParticipateChallengeService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;
    private final ChallengeStatusRepository challengeStatusRepository;

    @Transactional
    public void execute(Long id) {
        User user = userFacade.getCurrentUser();
        Challenge challenge = challengeFacade.getById(id);

        ChallengeStatus challengeStatus = ChallengeStatus.builder()
                .user(user)
                .challenge(challenge)
                .build();

        challengeStatusRepository.save(challengeStatus);
    }

}
