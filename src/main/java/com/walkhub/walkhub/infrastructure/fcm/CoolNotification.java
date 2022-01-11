package com.walkhub.walkhub.infrastructure.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.walkhub.walkhub.domain.notification.domain.NotificationEntity;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationRepository;
import com.walkhub.walkhub.infrastructure.fcm.dto.SendDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CoolNotification implements FcmUtil{

    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(SendDto sendDto) {
        Long notificationId = notificationRepository.save(
                NotificationEntity.builder()
                        .title(sendDto.getTitle())
                        .content(sendDto.getContent())
                        .build()
        ).getId();
        if(sendDto.getUser().haveDeviceToken()) {
            Message message = Message.builder()
                    .setToken(sendDto.getUser().getDeviceToken())
                    .putData("notification_id", notificationId.toString())
                    .setNotification(
                            Notification.builder()
                                    .setTitle(sendDto.getTitle())
                                    .setBody(sendDto.getContent())
                                    .build()
                    )
                    .build();
            FirebaseMessaging.getInstance().sendAsync(message);
        }
    }
}
