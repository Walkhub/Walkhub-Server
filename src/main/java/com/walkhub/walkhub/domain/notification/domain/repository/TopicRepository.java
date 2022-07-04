package com.walkhub.walkhub.domain.notification.domain.repository;

import com.walkhub.walkhub.domain.notification.domain.Topic;
import com.walkhub.walkhub.domain.notification.domain.type.NotificationType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Long> {

    Optional<Topic> findByType(NotificationType type);

}
