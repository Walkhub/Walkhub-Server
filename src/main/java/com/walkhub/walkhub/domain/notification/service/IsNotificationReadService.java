package com.walkhub.walkhub.domain.notification.service;

import com.walkhub.walkhub.domain.notification.domain.NotificationEntity;
import com.walkhub.walkhub.domain.notification.domain.NotificationList;
import com.walkhub.walkhub.domain.notification.domain.NotificationListId;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationListRepository;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationRepository;
import com.walkhub.walkhub.domain.notification.exception.NotificationNotFoundException;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IsNotificationReadService {

    private final NotificationListRepository notificationListRepository;
    private final NotificationRepository notificationRepository;
    private final UserFacade userFacade;

    public void execute(Long notification_id) {
        NotificationEntity notification = notificationRepository.findById(notification_id)
                .orElseThrow(() -> NotificationNotFoundException.EXCEPTION);

        User user = userFacade.getCurrentUser();

        NotificationListId notificationListId = NotificationListId.builder()
                .notificationEntity(notification_id)
                .user(user.getId())
                .build();

        NotificationList notificationList = notificationListRepository.findById(notificationListId)
                .orElseThrow(() -> NotificationNotFoundException.EXCEPTION);

        notificationList.updateNotificationList(notification, user);
    }

}
