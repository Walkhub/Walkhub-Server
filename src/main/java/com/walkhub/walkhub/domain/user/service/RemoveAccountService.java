package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeCollectionRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationListRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RemoveAccountService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeStatusRepository challengeStatusRepository;
    private final NotificationListRepository notificationListRepository;
    private final BadgeCollectionRepository badgeCollectionRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        userRepository.deleteById(user.getId());
        exerciseAnalysisRepository.deleteByUserId(user.getId());
        challengeRepository.deleteByUserId(user.getId());
        challengeStatusRepository.deleteNotOverChallengeStatusByUserId(user.getId());
        notificationListRepository.deleteByUserId(user.getId());
        badgeCollectionRepository.deleteByUserId(user.getId());

    }
}
