package com.walkhub.walkhub.domain.notification.service;

import com.walkhub.walkhub.domain.notification.domain.NotificationList;
import com.walkhub.walkhub.domain.notification.domain.NotificationListId;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationListRepository;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationRepository;
import com.walkhub.walkhub.domain.notification.exception.NotificationNotFoundException;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@WalkhubService
public class NotificationReadService {

    private final NotificationListRepository notificationListRepository;
    private final NotificationRepository notificationRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long notificationId) {
        if (notificationRepository.findById(notificationId).isEmpty()) {
            throw NotificationNotFoundException.EXCEPTION;
        }

        User user = userFacade.getCurrentUser();

        NotificationListId notificationListId = NotificationListId.builder()
                .notificationEntity(notificationId)
                .user(user.getId())
                .build();

        NotificationList notificationList = notificationListRepository.findById(notificationListId)
                .orElseThrow(() -> NotificationNotFoundException.EXCEPTION);

        notificationList.updateNotificationIsRead();
    }

}
