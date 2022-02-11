package com.walkhub.walkhub.domain.notification.presentation.dto.response;

import com.walkhub.walkhub.domain.notification.domain.type.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryNotificationListResponse {

    private final List<NotificationResponse> notificationList;

    @Getter
    @Builder
    public static class NotificationResponse{
        private final Long id;
        private final String title;
        private final String content;
        private final Type type;
        private final Long value;
        private final boolean isRead;
    }
}
