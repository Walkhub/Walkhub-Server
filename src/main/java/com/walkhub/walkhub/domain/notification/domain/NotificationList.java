package com.walkhub.walkhub.domain.notification.domain;

import com.walkhub.walkhub.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(NotificationListId.class)
@Entity
public class NotificationList {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id", nullable = false)
    private NotificationEntity notificationEntity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ColumnDefault("0")
    private Boolean isRead;

    @Builder
    public NotificationList(NotificationEntity notificationEntity, User userId) {
        this.notificationEntity = notificationEntity;
        this.user = userId;
        this.isRead = false;
    }

    public void updateNotificationIsRead() {
        this.isRead = true;
    }

}
