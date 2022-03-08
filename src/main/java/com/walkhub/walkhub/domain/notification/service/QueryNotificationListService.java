package com.walkhub.walkhub.domain.notification.service;

import com.walkhub.walkhub.domain.notification.domain.NotificationEntity;
import com.walkhub.walkhub.domain.notification.domain.NotificationList;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationListRepository;
import com.walkhub.walkhub.domain.notification.presentation.dto.response.QueryNotificationListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.walkhub.walkhub.domain.notification.presentation.dto.response.QueryNotificationListResponse.NotificationResponse;

@RequiredArgsConstructor
@WalkhubService
public class QueryNotificationListService {

    private final NotificationListRepository notificationListRepository;
    private final UserFacade userFacade;

    public QueryNotificationListResponse execute(Pageable pageable) {

        User user = userFacade.getCurrentUser();
        List<NotificationResponse> notificationLists = notificationListRepository.findByUser(user, pageable)
                .stream()
                .map(this::getNotification)
                .collect(Collectors.toList());

        return new QueryNotificationListResponse(notificationLists);
    }

    private NotificationResponse getNotification(NotificationList notificationList) {
        NotificationEntity notificationEntity = notificationList.getNotificationEntity();
        return NotificationResponse.builder()
                .id(notificationEntity.getId())
                .title(notificationEntity.getTitle())
                .content(notificationEntity.getContent())
                .type(notificationEntity.getType())
                .data(notificationEntity.getData())
                .isRead(notificationList.getIsRead())
                .build();
    }
    
}
