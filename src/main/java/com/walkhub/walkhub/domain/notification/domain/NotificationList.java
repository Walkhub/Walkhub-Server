package com.walkhub.walkhub.domain.notification.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(NotificationListId.class)
@Entity
public class NotificationList {

    @Id
    private Integer notificationId;

    @Id
    private Integer userId;

    private boolean isRead;

    @Builder
    public NotificationList(Integer notificationId, Integer userId) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.isRead = false;
    }

}
