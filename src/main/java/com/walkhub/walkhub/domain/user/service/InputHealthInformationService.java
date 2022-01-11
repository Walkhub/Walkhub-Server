package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.domain.type.HealthInfo;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.InputHealthInformationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InputHealthInformationService {

    private UserRepository userRepository;
    private UserFacade userFacade;

    public void execute(InputHealthInformationRequest request) {
        User user = userFacade.getCurrentUser();
        user.setHealthInfo(new HealthInfo(request.getWeight(), request.getHeight()));
        userRepository.save(user);
    }
}