package com.walkhub.walkhub.infrastructure.fcm;

import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationRequest;

public interface FcmUtil {

    void initialize();

    void sendNotification(NotificationRequest sendDto);
}
