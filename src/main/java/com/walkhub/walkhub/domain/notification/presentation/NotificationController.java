package com.walkhub.walkhub.domain.notification.presentation;

import com.walkhub.walkhub.domain.notification.presentation.dto.response.QueryNotificationListResponse;
import com.walkhub.walkhub.domain.notification.service.NotificationReadService;
import com.walkhub.walkhub.domain.notification.service.QueryNotificationListService;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.infrastructure.fcm.FcmUtil;
import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationReadService notificationReadService;
    private final QueryNotificationListService queryNotificationListService;
    private final FcmUtil fcmUtil;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{notification-id}")
    public void notificationRead(@PathVariable("notification-id") Long notificationId) {
        notificationReadService.execute(notificationId);
    }

    @GetMapping
    public QueryNotificationListResponse notificationList(Pageable pageable) {
        return queryNotificationListService.execute(pageable);
    }

    @PatchMapping
    public void unSubscribeTopic(List<User> users, NotificationRequest request) {
        fcmUtil.unSubscribeTopic(users, request);
    }

    @PatchMapping("/on")
    public void subscribeTopic(List<User> users, NotificationRequest request) {
        fcmUtil.subscribeTopic(users, request);
    }

}
