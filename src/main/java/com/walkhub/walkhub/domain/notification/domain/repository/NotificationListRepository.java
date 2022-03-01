package com.walkhub.walkhub.domain.notification.domain.repository;

import com.walkhub.walkhub.domain.notification.domain.NotificationList;
import com.walkhub.walkhub.domain.notification.domain.NotificationListId;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface NotificationListRepository extends CrudRepository<NotificationList, NotificationListId> {
    Page<NotificationList> findByUser(User user, Pageable pageable);
    void deleteByUserId(Long userId);
}
