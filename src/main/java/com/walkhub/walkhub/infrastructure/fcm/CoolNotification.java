package com.walkhub.walkhub.infrastructure.fcm;

import com.google.firebase.messaging.*;
import com.walkhub.walkhub.domain.notification.domain.NotificationEntity;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.infrastructure.fcm.dto.SendDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

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

        List<String> deviceTokens = sendDto.getUsers().stream().map(User::getDeviceToken).collect(Collectors.toList());

        MulticastMessage message = MulticastMessage.builder()
                .putData("notification_id", notificationId.toString())
                .putData("click_action",sendDto.getClickAction())
                .putData("value", sendDto.getValue())
                .setNotification(
                        Notification.builder()
                                .setTitle(sendDto.getTitle())
                                .setBody(sendDto.getContent())
                                .build()
                )
                .addAllTokens(deviceTokens)
                .build();
        FirebaseMessaging.getInstance().sendMulticastAsync(message);
    }
}
