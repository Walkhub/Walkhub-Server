package com.walkhub.walkhub.domain.notification.service;

import com.walkhub.walkhub.domain.notification.domain.Topic;
import com.walkhub.walkhub.domain.notification.domain.repository.TopicRepository;
import com.walkhub.walkhub.domain.notification.presentation.dto.response.NotificationStatusResponse;
import com.walkhub.walkhub.domain.notification.presentation.dto.response.NotificationStatusResponse.StatusResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryNotificationStatusListService {

    private final UserFacade userFacade;
    private final TopicRepository topicRepository;

    public NotificationStatusResponse execute() {
        User user = userFacade.getCurrentUser();

        List<StatusResponse> whetherList = topicRepository.findAllByUser(user)
                .stream()
                .map(this::topicWhetherBuilder)
                .collect(Collectors.toList());

        return new NotificationStatusResponse(whetherList);
    }

    private StatusResponse topicWhetherBuilder(Topic topic) {
        return StatusResponse.builder()
                .id(topic.getId())
                .type(topic.getType())
                .isSubscribe(topic.getIsSubscribe())
                .build();
    }

}