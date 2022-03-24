package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ExitSectionService {

    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;
    private final ChallengeStatusRepository challengeStatusRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        if (Authority.TEACHER.equals(user.getAuthority())) {
            challengeRepository.deleteAllByUserAndUserScopeAndEndAtAfter(user, UserScope.CLASS, LocalDate.now());
        }
        challengeStatusRepository.deleteNotOverChallengeStatusByUserId(user.getId());

        user.setSectionNull();
    }

}
