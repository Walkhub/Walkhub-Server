package com.walkhub.walkhub.domain.notification.presentation.dto.response;

import com.walkhub.walkhub.domain.notification.domain.type.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NotificationStatusResponse {

    private final List<StatusResponse> statusResponseList;

    @Getter
    @Builder
    public static class StatusResponse {
        private final Long id;
        private final NotificationType type;
        private final Boolean isSubscribe;
    }

}
