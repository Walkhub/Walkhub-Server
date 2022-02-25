package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.type.HealthInfo;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.InputHealthInformationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InputHealthInformationService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(InputHealthInformationRequest request) {
        User user = userFacade.getCurrentUser();
        if (request.getHeight() != null || request.getWeight() != null) {
            user.setHealthInfo(new HealthInfo(request.getWeight(), request.getHeight()));
        }
        if (request.getSex() != null) {
            user.setSex(request.getSex());
        }
    }
}