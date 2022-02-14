package com.walkhub.walkhub.infrastructure.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.walkhub.walkhub.domain.notification.domain.NotificationEntity;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.infrastructure.fcm.dto.SendDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class CoolNotification implements FcmUtil {

    private final NotificationRepository notificationRepository;

    @Value("${firebase.path}")
    private String path;

    @PostConstruct
    @Override
    public void initialize() {
        try {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials
                                .fromStream(new ClassPathResource(path)
                                        .getInputStream()))
                        .build();

                if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public void sendNotification(SendDto sendDto) {
        Long notificationId = notificationRepository.save(
                NotificationEntity.builder()
                        .title(sendDto.getTitle())
                        .content(sendDto.getContent())
                        .build()
        ).getId();

        List<String> deviceTokens = sendDto.getUsers()
                .stream().map(User::getDeviceToken)
                .collect(Collectors.toList());

        MulticastMessage message = MulticastMessage.builder()
                .putData("notification_id", notificationId.toString())
                .putData("click_action", sendDto.getClickAction())
                .putData("value", sendDto.getValue())
                .setNotification(
                        Notification.builder()
                                .setTitle(sendDto.getTitle())
                                .setBody(sendDto.getContent())
                                .build()
                )
                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setSound("default")
                                .build())
                        .build())
                .addAllTokens(deviceTokens)
                .build();
        FirebaseMessaging.getInstance().sendMulticastAsync(message);
    }
}
