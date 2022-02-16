package com.walkhub.walkhub.infrastructure.fcm.dto.request;

import com.walkhub.walkhub.domain.notification.domain.type.Type;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationSendRequest {
    private final User user;
    private final String title;
    private final String content;
    private final String value;
    private final Type type;
    private final UserScope userScope;
    private final String clickAction;
}
