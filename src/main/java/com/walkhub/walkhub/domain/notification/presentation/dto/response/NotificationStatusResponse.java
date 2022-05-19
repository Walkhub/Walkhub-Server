package com.walkhub.walkhub.domain.notification.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NotificationStatusResponse {

    private final List<WhetherResponse> whetherResponseList;

    @Getter
    @Builder
    public static class WhetherResponse {
        private final Long id;
        private final String title;
        private final Boolean isSubscribe;
    }

}
