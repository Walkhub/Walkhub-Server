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
import com.walkhub.walkhub.domain.notification.domain.Topic;
import com.walkhub.walkhub.domain.notification.domain.TopicList;
import com.walkhub.walkhub.domain.notification.domain.TopicListId;
import com.walkhub.walkhub.domain.notification.domain.repository.NotificationRepository;
import com.walkhub.walkhub.domain.notification.domain.repository.TopicListRepository;
import com.walkhub.walkhub.domain.notification.exception.TopicNotFoundException;
import com.walkhub.walkhub.domain.notification.facade.TopicFacade;
import com.walkhub.walkhub.domain.notification.presentation.dto.request.SubscribeRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationInformation;
import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationRequest;
import com.walkhub.walkhub.infrastructure.fcm.type.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
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

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final TopicListRepository topicListRepository;
    private final TopicFacade topicFacade;
    private final UserFacade userFacade;

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
                        .topic(request.getTopic())
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
                .setTopic((request.getTopic().toString()))
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }

    @Transactional
    @Override
    public void subscribeTopic(SubscribeRequest request) {
        subscribeToQueriedTopics(request, true);
    }

    @Transactional
    @Override
    public void unSubscribeTopic(SubscribeRequest request) {
        subscribeToQueriedTopics(request, false);
    }

    private void subscribeToQueriedTopics(SubscribeRequest request, boolean isSubscribing) {
        List<User> userList = userRepository.findAllByIdIn(request.getUserIdList());
        Topic topic = topicFacade.getTopicByType(request.getType());

        subscribeTopicInFirebase(userList, topic, isSubscribing);
    }

    private void subscribeTopicInFirebase(List<User> userList, Topic topic, boolean isSubscribing) {
        for (int i = 0; i < userList.size() / 1000; i++) {
            List<String> deviceTokenListToSubscribe = userList.subList(i * 1000, 1000 * i + 1000)
                    .stream().map(User::getDeviceToken)
                    .collect(Collectors.toList());

            FirebaseMessaging instance = FirebaseMessaging.getInstance(FirebaseApp.getInstance());

            try {
                if (isSubscribing) {
                    instance.subscribeToTopic(deviceTokenListToSubscribe, topic.toString());
                    subscribeTopicEntity(topic, isSubscribing);
                } else {
                    instance.unsubscribeFromTopic(deviceTokenListToSubscribe, topic.toString());
                    subscribeTopicEntity(topic, isSubscribing);
                }
            } catch (FirebaseMessagingException e) {
                log.error(e.getMessage());
            }

        }
    }

    private void subscribeTopicEntity(Topic topic, boolean isSubscribing) {
        User user = userFacade.getCurrentUser();

        TopicListId topicListId = TopicListId.builder()
                .topic(topic)
                .user(user)
                .build();

        TopicList topicList = topicListRepository.findById(topicListId)
                .orElseThrow(() -> TopicNotFoundException.EXCEPTION);

        if (isSubscribing) {
            topicList.getId().subscribeTopic();
        } else {
            topicList.getId().snSubscribeTopic();
        }
    }

    @Override
    public void sendNoticeNotification(Notice notice) {
        noticeSendNotificationBuilder(notice);
    }

    @Override
    public void sendChallengeExpirationNotification(Challenge challenge) {
        if (LocalDate.now().isEqual(challenge.getEndAt())) {
            challengeSendNotificationBuilder(challenge, ContentType.EXPIRED_CHALLENGE);
        }
        throw ChallengeNotExpirationException.EXCEPTION;

    }

    @Override
    public void sendChallengeSuccessNotification(Challenge challenge, Exercise exercise) {
        if (exercise.getWalkCount().equals(challenge.getGoal())) {
            challengeSendNotificationBuilder(challenge, ContentType.SUCCESS_CHALLENGE);
        }
        throw ChallengeNotSuccessException.EXCEPTION;

    }

    @Override
    public void sendChallengeCanParticipate(Challenge challenge) {
        challengeSendNotificationBuilder(challenge, ContentType.CAN_PARTICIPATE_CHALLENGE);
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
                notificationBuilder(NotificationInformation.challengeNotificationInformation(challenge),
                        "축하드려요!" + " [ " + challenge.getName() + " ]" + contentType.getContent())
        );

    }

    private void noticeSendNotificationBuilder(Notice notice) {
        sendNotification(
                notificationBuilder(NotificationInformation.noticeNotificationInformation(notice),
                        " [ " + notice.getTitle() + " ] " + ContentType.CREATE_NOTICE.getContent())
        );
    }

}
