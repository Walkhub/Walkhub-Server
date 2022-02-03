package com.walkhub.walkhub.domain.notification.presentation;

import com.walkhub.walkhub.domain.notification.service.NotificationReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationReadService notificationReadService;

    @PatchMapping("/{notification-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void notificationRead(@PathVariable("notification-id") Long notificationId) {
        notificationReadService.execute(notificationId);
    }

}
