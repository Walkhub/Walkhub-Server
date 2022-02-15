package com.walkhub.walkhub.domain.notification.service;

import com.walkhub.walkhub.domain.notification.domain.NotificationEntity;
import com.walkhub.walkhub.domain.notification.domain.NotificationList;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationListRepository;
import com.walkhub.walkhub.domain.notification.presentation.dto.response.QueryNotificationListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.walkhub.walkhub.domain.notification.presentation.dto.response.QueryNotificationListResponse.*;

@RequiredArgsConstructor
@Service
public class QueryNotificationListService {

    private final NotificationListRepository notificationListRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
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
                .value(notificationEntity.getValue())
                .isRead(notificationList.getIsRead())
                .build();
    }
    
}
