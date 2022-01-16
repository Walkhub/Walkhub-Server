package com.walkhub.walkhub.domain.notification.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
public class NotificationListId implements Serializable {

    private Long notificationEntity;

    private Long user;

}
