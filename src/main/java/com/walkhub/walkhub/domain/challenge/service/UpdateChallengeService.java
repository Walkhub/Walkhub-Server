package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.UpdateChallengeRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateChallengeService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;

    @Transactional
    public void execute(Long id, UpdateChallengeRequest request) {
        User user = userFacade.getCurrentUser();
        Challenge challenge = challengeFacade.getChallengeById(id);

        if (!challenge.getUser().equals(user)) {
            throw InvalidRoleException.EXCEPTION;
        }

        challenge.updateChallenge(request);
    }
}
