package com.walkhub.walkhub.domain.notification.service;

import com.walkhub.walkhub.domain.notification.domain.Topic;
import com.walkhub.walkhub.domain.notification.domain.repository.TopicRepository;
import com.walkhub.walkhub.domain.notification.presentation.dto.response.NotificationWhetherResponse;
import com.walkhub.walkhub.domain.notification.presentation.dto.response.NotificationWhetherResponse.WhetherResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryNotificationWhetherListService {

    private final UserFacade userFacade;
    private final TopicRepository topicRepository;

    public NotificationWhetherResponse execute() {
        User user = userFacade.getCurrentUser();

        List<WhetherResponse> whetherList = topicRepository.findByUser(user)
                .stream()
                .map(this::topicWhetherBuilder)
                .collect(Collectors.toList());

        return new NotificationWhetherResponse(whetherList);
    }

    private WhetherResponse topicWhetherBuilder(Topic topic) {
        return WhetherResponse.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .isSubscribe(topic.getIsSubscribe())
                .build();
    }

}
