package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ExitSectionService {

    private final UserFacade userFacade;
    private final ChallengeStatusRepository challengeStatusRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        challengeStatusRepository.deleteNotOverChallengeStatusByUserId(user.getId());
        user.setSectionNull();
    }

}
