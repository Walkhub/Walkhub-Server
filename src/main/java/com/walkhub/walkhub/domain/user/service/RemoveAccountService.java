package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeCollectionRepository;
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
    private final ChallengeStatusRepository challengeStatusRepository;
    private final UserRepository userRepository;
    private final NotificationListRepository notificationListRepository;
    private final BadgeCollectionRepository badgeCollectionRepository;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        challengeStatusRepository.deleteNotOverChallengeStatusByUserId(user.getId());
        userRepository.deleteById(user.getId());
        userRepository.deleteById(user.getId());
        notificationListRepository.deleteAll();
        badgeCollectionRepository.deleteAll();
        exerciseAnalysisRepository.deleteAll();
    }
}
