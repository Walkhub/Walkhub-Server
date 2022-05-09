package com.walkhub.walkhub.domain.notification.presentation.dto.request;

import com.walkhub.walkhub.domain.notification.domain.type.NotificationType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SubscribeRequest {

    @NotNull(message = "List는 Null일 수 없습니다.")
    private Long userId;

    @NotNull(message = "topic은 Null일 수 없습니다.")
    private NotificationType type;

}
