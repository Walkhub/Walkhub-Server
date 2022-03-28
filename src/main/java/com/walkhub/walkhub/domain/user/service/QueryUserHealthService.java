package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.HealthResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryUserHealthService {

    private final UserFacade userFacade;

    public HealthResponse execute() {
        User user = userFacade.getCurrentUser();

        return HealthResponse.builder()
                .height(user.getHeight())
                .weight(user.getWeight())
                .sex(user.getSex())
                .build();
    }

}
