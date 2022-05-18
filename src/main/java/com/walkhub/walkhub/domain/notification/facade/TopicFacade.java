package com.walkhub.walkhub.domain.notification.facade;

import com.walkhub.walkhub.domain.notification.domain.Topic;
import com.walkhub.walkhub.domain.notification.domain.repository.TopicRepository;
import com.walkhub.walkhub.domain.notification.exception.TopicNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicFacade {

    private final TopicRepository topicRepository;

    public Topic getTopicByTitle(String title) {
        return topicRepository.findByTitle(title)
                .orElseThrow(() -> TopicNotFoundException.EXCEPTION);
    }

}
