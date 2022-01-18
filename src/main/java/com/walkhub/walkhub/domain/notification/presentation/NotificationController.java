package com.walkhub.walkhub.domain.notification.presentation;

import com.walkhub.walkhub.domain.notification.service.NotificationReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationReadService notificationReadService;

    @PatchMapping("/{notification-id}")
    public void isNotificationRead(@PathVariable("notification-id") Long notificationId) {
        notificationReadService.execute(notificationId);
    }

}
