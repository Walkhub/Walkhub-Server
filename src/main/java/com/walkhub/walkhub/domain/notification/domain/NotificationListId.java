package com.walkhub.walkhub.domain.notification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class NotificationListId implements Serializable {

    private Long notification;

    private Long user;

}
