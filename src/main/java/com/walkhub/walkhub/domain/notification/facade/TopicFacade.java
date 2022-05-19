package com.walkhub.walkhub.domain.notification.facade;

import com.walkhub.walkhub.domain.notification.domain.Topic;
import com.walkhub.walkhub.domain.notification.domain.repository.TopicRepository;
import com.walkhub.walkhub.domain.notification.domain.type.NotificationType;
import com.walkhub.walkhub.domain.notification.exception.TopicNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicFacade {

    private final TopicRepository topicRepository;

    public Topic getTopicByType(NotificationType type) {
        return topicRepository.findByType(type)
                .orElseThrow(() -> TopicNotFoundException.EXCEPTION);
    }

}
