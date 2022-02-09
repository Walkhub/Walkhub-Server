package com.walkhub.walkhub.infrastructure.fcm.dto;

import com.walkhub.walkhub.domain.notification.domain.type.Type;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SendDto {
    private final List<User> users;
    private final String title;
    private final String content;
    private final String value;
    private final Type type;
    private final UserScope userScope;
    private final String clickAction;
}
