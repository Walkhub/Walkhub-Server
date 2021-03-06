package com.walkhub.walkhub.infrastructure.fcm.dto.request;

import com.walkhub.walkhub.domain.notification.domain.Topic;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationRequest {
    private final User user;
    private final String title;
    private final String content;
    private final String data;
    private final Topic topic;
    private final UserScope userScope;
    private final String clickAction;
}
