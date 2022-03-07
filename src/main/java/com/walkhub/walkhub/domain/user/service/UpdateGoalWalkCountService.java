package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateGoalWalkCountRequest;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@WalkhubService
public class UpdateGoalWalkCountService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(UpdateGoalWalkCountRequest request) {
        User user = userFacade.getCurrentUser();

        user.updateDailyWalkCountGoal(request.getDailyWalkCountGoal());
    }
}
