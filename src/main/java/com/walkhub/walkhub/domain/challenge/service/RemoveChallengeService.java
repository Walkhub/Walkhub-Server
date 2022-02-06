package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.exception.NotMySchoolException;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RemoveChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;

    @Transactional
    public void execute(Long id) {
        User user = userFacade.getCurrentUser();
        Challenge challenge = challengeFacade.getById(id);

        if (!Authority.TCHR.equals(user.getAuthority())) {
            throw InvalidRoleException.EXCEPTION;
        }

        if (!challenge.isMySchool(user.getRealSchoolAgencyCode())){
            throw NotMySchoolException.EXCEPTION;
        }

        challengeRepository.delete(challenge);
    }

}
