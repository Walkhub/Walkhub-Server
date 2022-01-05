package com.walkhub.walkhub.domain.notification.domain.repository;

import com.walkhub.walkhub.domain.notification.domain.NotificationList;
import com.walkhub.walkhub.domain.notification.domain.NotificationListId;
import org.springframework.data.repository.CrudRepository;

public interface NotificationListRepository extends CrudRepository<NotificationList, NotificationListId> {
}
