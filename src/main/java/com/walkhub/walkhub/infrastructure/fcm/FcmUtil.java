package com.walkhub.walkhub.infrastructure.fcm;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationRequest;

import java.util.List;

public interface FcmUtil {

    void initialize();

    void sendNotification(NotificationRequest sendDto);


    void subscribeTopic(List<User> users, NotificationRequest request) throws FirebaseMessagingException;


    void unSubscribeTopic(List<User> users, NotificationRequest request) throws FirebaseMessagingException;
}
