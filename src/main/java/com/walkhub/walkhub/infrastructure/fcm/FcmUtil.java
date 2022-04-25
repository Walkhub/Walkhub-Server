package com.walkhub.walkhub.infrastructure.fcm;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.notification.presentation.dto.request.SubscribeRequest;
import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationRequest;

public interface FcmUtil {

    void initialize();

    void sendNotification(NotificationRequest sendDto);

    void subscribeTopic(SubscribeRequest request);

    void unSubscribeTopic(SubscribeRequest request);

    void sendNoticeNotification(Notice notice);

    void sendChallengeExpirationNotification(Challenge challenge);

    void sendChallengeSuccessNotification(Challenge challenge, Exercise exercise);

    void sendChallengeCanParticipate(Challenge challenge);

}
