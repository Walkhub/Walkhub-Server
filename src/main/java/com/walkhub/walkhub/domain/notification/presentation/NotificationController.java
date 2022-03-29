package com.walkhub.walkhub.domain.notification.presentation;

import com.walkhub.walkhub.domain.notification.presentation.dto.request.SubscribeRequest;
import com.walkhub.walkhub.domain.notification.presentation.dto.response.QueryNotificationListResponse;
import com.walkhub.walkhub.domain.notification.service.NotificationReadService;
import com.walkhub.walkhub.domain.notification.service.QueryNotificationListService;
import com.walkhub.walkhub.infrastructure.fcm.FcmUtil;
import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void unSubscribeTopic(@RequestBody @Valid SubscribeRequest userRequest, NotificationRequest request) {
        fcmUtil.unSubscribeTopic(userRequest.getUsers(), request);
    }

    @PatchMapping("/on")
    public void subscribeTopic(@RequestBody @Valid SubscribeRequest userRequest, NotificationRequest request) {
        fcmUtil.subscribeTopic(userRequest.getUsers(), request);
    }

}
