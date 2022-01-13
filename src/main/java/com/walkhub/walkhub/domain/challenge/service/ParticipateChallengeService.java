package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.exception.AlreadyParticipatedException;
import com.walkhub.walkhub.domain.challenge.exception.InvalidScopeException;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Scope;
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

        isAlreadyParticipated(user, challenge);
        verifyScope(user, challenge);

        ChallengeStatus challengeStatus = ChallengeStatus.builder()
                .user(user)
                .challenge(challenge)
                .build();

        challengeStatusRepository.save(challengeStatus);
    }

    private void isAlreadyParticipated(User user, Challenge challenge) {
        if (challengeStatusRepository.findByUserAndChallenge(user, challenge).isPresent()) {
            throw AlreadyParticipatedException.EXCEPTION;
        }
    }

    private void verifyScope(User user, Challenge challenge) {
        Scope scope = challenge.getScope();
        User writer = challenge.getUser();

        if (
                (scope == Scope.SCH
                        && !user.getSchool().equals(writer.getSchool())
                )
                || (scope == Scope.CLS
                        && !user.getGroup().getClassNum().equals(writer.getGroup().getClassNum())
                )
        ) {
            throw InvalidScopeException.EXCEPTION;
        }

    }

}
