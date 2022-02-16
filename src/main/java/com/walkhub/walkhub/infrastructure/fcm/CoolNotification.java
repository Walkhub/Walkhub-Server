package com.walkhub.walkhub.infrastructure.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.walkhub.walkhub.domain.notification.domain.NotificationEntity;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationRepository;
import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

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
    public void sendNotification(NotificationRequest request) {
        String condition = "'notice' in topics || 'challenge' in topics || 'exercise' in topics || 'cheering' in topics";
        Long notificationId = notificationRepository.save(
                NotificationEntity.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .type(request.getType())
                        .data(request.getData())
                        .userScope(request.getUserScope())
                        .build()
        ).getId();

        Message message = Message.builder()
                .putData("notification-id", notificationId.toString())
                .putData("click_action", request.getClickAction())
                .setCondition(condition)
                .setNotification(
                        Notification.builder()
                                .setTitle(request.getTitle())
                                .setBody(request.getContent())
                                .build()
                )
                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setSound("default")
                                .build())
                        .build())
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
