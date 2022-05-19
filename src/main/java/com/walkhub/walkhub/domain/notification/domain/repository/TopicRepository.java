package com.walkhub.walkhub.domain.notification.domain.repository;

import com.walkhub.walkhub.domain.notification.domain.Topic;
import com.walkhub.walkhub.domain.notification.domain.type.NotificationType;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Long> {
    List<Topic> findAllByUser(User user);

    Optional<Topic> findByType(NotificationType type);

}
