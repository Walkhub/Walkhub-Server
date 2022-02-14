package com.walkhub.walkhub.infrastructure.fcm;

import com.walkhub.walkhub.domain.notification.domain.type.Type;
import com.walkhub.walkhub.infrastructure.fcm.dto.SendDto;

public interface FcmUtil {

    void initialize();

    void sendNotification(SendDto sendDto, Type type);
}
