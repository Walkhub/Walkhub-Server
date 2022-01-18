package com.walkhub.walkhub.domain.notification.domain;

import com.walkhub.walkhub.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(NotificationListId.class)
@Entity
public class NotificationList {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    private NotificationEntity notificationEntity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Boolean isRead;

    @Builder
    public NotificationList(NotificationEntity notificationEntity, User userId) {
        this.notificationEntity = notificationEntity;
        this.user = userId;
        this.isRead = false;
    }

    public void updateNotificationList() {
        this.isRead = true;
    }

}
