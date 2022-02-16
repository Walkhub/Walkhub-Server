package com.walkhub.walkhub.infrastructure.fcm;

import com.walkhub.walkhub.infrastructure.fcm.dto.request.NotificationSendRequest;

public interface FcmUtil {

    void initialize();

    void sendNotification(NotificationSendRequest sendDto);
}
