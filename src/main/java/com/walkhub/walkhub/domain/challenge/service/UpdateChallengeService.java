package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.ChallengeRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.UserScope;
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
    public void execute(Long id, ChallengeRequest request) {
        User user = userFacade.getCurrentUser();
        Challenge challenge = challengeFacade.getChallengeById(id);

        if (!challenge.getUser().equals(user)) {
            throw InvalidRoleException.EXCEPTION;
        }

        if (challenge.getUserScope() == UserScope.SCHOOL) {
            if (UserScope.GRADE.equals(request.getUserScope()) || UserScope.CLASS.equals(request.getUserScope())) {
                throw InvalidRoleException.EXCEPTION;
            }
        } else if (UserScope.GRADE.equals(challenge.getUserScope())) {
            if (UserScope.CLASS.equals(request.getUserScope())) {
                throw InvalidRoleException.EXCEPTION;
            }
        } else if (UserScope.CLASS.equals(challenge.getUserScope())) {
            throw InvalidRoleException.EXCEPTION;
        }

        challenge.updateChallenge(request);
    }
}
