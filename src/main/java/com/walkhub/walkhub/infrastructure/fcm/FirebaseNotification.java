package com.walkhub.walkhub.infrastructure.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.exception.ChallengeNotExpirationException;
import com.walkhub.walkhub.domain.challenge.exception.ChallengeNotSuccessException;
import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.notification.domain.NotificationEntity;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationInformation;
import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationRequest;
import com.walkhub.walkhub.infrastructure.fcm.type.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("EqualsBetweenInconvertibleTypes")
@Slf4j
@RequiredArgsConstructor
@Component
public class FirebaseNotification implements FcmUtil {

    private final NotificationRepository notificationRepository;

    @Value("${firebase.path}")
    private String path;

    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = {MESSAGING_SCOPE};

    @PostConstruct
    @Override
    public void initialize() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(path).getInputStream())
                            .createScoped(Arrays.asList(SCOPES)))
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
                .setToken(request.getUser().getDeviceToken())
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
                .setTopic((request.getType().toString()))
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }

    @Override
    public void subscribeTopic(List<User> users, NotificationRequest request) {
        try {
            for (int i = 0; i < users.size() / 1000; i++) {
                List<String> deviceTokenListToSubscribe = users.subList(i, i * 1000)
                        .stream().map(User::getDeviceToken)
                        .collect(Collectors.toList());

                TopicManagementResponse response = FirebaseMessaging.getInstance(FirebaseApp.getInstance())
                        .subscribeToTopic(
                                deviceTokenListToSubscribe, request.getType().toString()
                        );
            }
        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void unSubscribeTopic(List<User> users, NotificationRequest request) {
        try {
            for (int i = 0; i < users.size() / 1000; i++) {
                List<String> deviceTokenListToSubscribe = users.subList(i, i * 1000)
                        .stream().map(User::getDeviceToken)
                        .collect(Collectors.toList());

                TopicManagementResponse response = FirebaseMessaging.getInstance(FirebaseApp.getInstance())
                        .unsubscribeFromTopic(
                                deviceTokenListToSubscribe, request.getType().toString()
                        );
            }
        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendNoticeNotification(Notice notice, ContentType contentType) {
        noticeSendNotificationBuilder(notice, contentType);
    }

    @Override
    public void sendChallengeExpirationNotification(Challenge challenge, ContentType contentType) {
        if (LocalDate.now().isEqual(challenge.getEndAt())) {
            challengeSendNotificationBuilder(challenge, contentType);
        } throw ChallengeNotExpirationException.EXCEPTION;

    }

    @Override
    public void sendChallengeSuccessNotification(Challenge challenge, ContentType contentType, Exercise exercise) {
        if (exercise.getWalkCount().equals(challenge.getGoal())) {
            challengeSendNotificationBuilder(challenge, contentType);
        } throw ChallengeNotSuccessException.EXCEPTION;

    }

    @Override
    public void sendChallengeCanParticipate(Challenge challenge, ContentType contentType) {
        challengeSendNotificationBuilder(challenge, contentType);
    }

    private NotificationRequest notificationBuilder(NotificationInformation notificationInformation, String content) {
        return NotificationRequest.builder()
                .user(notificationInformation.getUser())
                .title(notificationInformation.getTitle())
                .content(content)
                .data(notificationInformation.getData())
                .userScope(notificationInformation.getScope())
                .build();
    }

    private void challengeSendNotificationBuilder(Challenge challenge, ContentType contentType) {
        sendNotification(
                notificationBuilder(NotificationInformation.challengeNotificationInformation(challenge), " [ " + challenge.getName() + " ]" + contentType.getContent())
        );

    }

    private void noticeSendNotificationBuilder(Notice notice, ContentType contentType) {
        sendNotification(
                notificationBuilder(NotificationInformation.noticeNotificationInformation(notice), " [ " + notice.getTitle() + " ] " + contentType.getContent())
        );
    }

}
