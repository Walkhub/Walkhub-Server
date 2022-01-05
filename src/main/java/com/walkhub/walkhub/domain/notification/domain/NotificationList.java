package com.walkhub.walkhub.domain.notification.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(NotificationListId.class)
@Entity
public class NotificationList {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    private Notification notificationId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    private boolean isRead;

    @Builder
    public NotificationList(Notification notificationId, User userId) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.isRead = false;
    }

}
