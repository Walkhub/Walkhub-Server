package com.walkhub.walkhub.domain.notification.domain.repository;

import com.walkhub.walkhub.domain.notification.domain.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
