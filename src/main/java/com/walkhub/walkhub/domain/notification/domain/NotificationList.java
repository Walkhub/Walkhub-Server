package com.walkhub.walkhub.domain.notification.domain;

import com.walkhub.walkhub.domain.user.domain.User;
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
    private Notification notification;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isRead;

    @Builder
    public NotificationList(Notification notificationId, User userId) {
        this.notification = notificationId;
        this.user = userId;
        this.isRead = false;
    }

}
