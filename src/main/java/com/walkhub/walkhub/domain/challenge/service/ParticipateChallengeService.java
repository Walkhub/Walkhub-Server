package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.exception.AlreadyParticipatedException;
import com.walkhub.walkhub.domain.challenge.exception.InvalidScopeException;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ParticipateChallengeService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;
    private final ChallengeStatusRepository challengeStatusRepository;

    @Transactional
    public void execute(Long challengeId) {
        Challenge challenge = challengeFacade.getChallengeById(challengeId);
        User user = userFacade.getCurrentUser();
        User writer = challenge.getUser();

        if (!verifyScope(user, writer, challenge)) {
            throw InvalidScopeException.EXCEPTION;
        }

        if (challengeStatusRepository.findByChallengeAndUser(challenge, user).isPresent()) {
            throw AlreadyParticipatedException.EXCEPTION;
        }

        ChallengeStatus challengeStatus = ChallengeStatus.builder()
                .challenge(challenge)
                .user(user)
                .build();

        challengeStatusRepository.save(challengeStatus);
    }

    private boolean verifyScope(User user, User writer, Challenge challenge) {
        Section userSection = user.hasSection() ? user.getSection() : Section.builder().build();
        Section writerSection = writer.hasSection() ? user.getSection() : Section.builder().build();

        switch (challenge.getUserScope()) {
            case SCHOOL: {
                return user.getSchool().equals(writer.getSchool());
            }
            case GRADE: {
                return userSection.getGrade().equals(writerSection.getGrade());
            }
            case CLASS: {
                return userSection.getClassNum().equals(writerSection.getClassNum());
            }
            default:
                return true;
        }
    }

}
