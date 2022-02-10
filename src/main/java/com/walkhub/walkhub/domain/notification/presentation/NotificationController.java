package com.walkhub.walkhub.domain.notification.presentation;

import com.walkhub.walkhub.domain.notification.domain.NotificationEntity;
import com.walkhub.walkhub.domain.notification.presentation.dto.response.QueryNotificationListResponse;
import com.walkhub.walkhub.domain.notification.service.NotificationReadService;
import com.walkhub.walkhub.domain.notification.service.QueryNotificationListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationReadService notificationReadService;
    private final QueryNotificationListService queryNotificationListService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{notification-id}")
    public void notificationRead(@PathVariable("notification-id") Long notificationId) {
        notificationReadService.execute(notificationId);
    }

    @GetMapping("/")
    public QueryNotificationListResponse notificationList(Pageable pageable, NotificationEntity notificationEntity) {
        return queryNotificationListService.execute(pageable,notificationEntity);
    }
}
