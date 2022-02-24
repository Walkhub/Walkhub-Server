package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeCollectionRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.LocationRepository;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationListRepository;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationRepository;
import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RemoveAccountService {

    private final UserFacade userFacade;
    private final SchoolRepository schoolRepository;
    private final SectionRepository sectionRepository;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeStatusRepository challengeStatusRepository;
    private final NotificationListRepository notificationListRepository;
    private final NotificationRepository notificationRepository;
    private final ExerciseRepository exerciseRepository;
    private final LocationRepository locationRepository;
    private final BadgeCollectionRepository badgeCollectionRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        schoolRepository.deleteById(user.getId());
        sectionRepository.deleteById(user.getId());
        exerciseAnalysisRepository.deleteAll();
        challengeRepository.deleteById(user.getId());
        challengeStatusRepository.deleteAll();
        notificationListRepository.deleteAll();
        notificationRepository.deleteAll();
        exerciseRepository.deleteAll();
        locationRepository.deleteAll();
        badgeCollectionRepository.deleteAll();

    }
}
