package com.walkhub.walkhub.infrastructure.fcm.dto;

import com.walkhub.walkhub.domain.notification.domain.type.Type;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.Scope;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendDto {
    private final User user;
    private final String title;
    private final String content;
    private final Long value;
    private final Type type;
    private final Scope scope;
}
