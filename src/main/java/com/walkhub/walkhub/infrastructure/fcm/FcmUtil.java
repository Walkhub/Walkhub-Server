package com.walkhub.walkhub.infrastructure.fcm;

import com.walkhub.walkhub.infrastructure.fcm.dto.SendDto;

public interface FcmUtil {
    void sendNotification(SendDto sendDto);
}
