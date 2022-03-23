package com.walkhub.walkhub.domain.calorielevel.service;

import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import com.walkhub.walkhub.domain.calorielevel.domain.repository.CalorieLevelRepository;
import com.walkhub.walkhub.domain.calorielevel.exception.CalorieLevelNotFoundException;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class UpdateMaxCalorieLevelService {

    private final UserFacade userFacade;
    private final CalorieLevelRepository calorieLevelRepository;

    @Transactional
    public void execute(Long levelId) {
        User user = userFacade.getCurrentUser();

        CalorieLevel calorieLevel = calorieLevelRepository.findById(levelId)
                .orElseThrow(() -> CalorieLevelNotFoundException.EXCEPTION);

        if (user.getMaxLevel().getLevel() < calorieLevel.getLevel()) {
            user.setMaxLevel(calorieLevel);
        }
    }

}
