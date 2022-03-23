package com.walkhub.walkhub.domain.calorielevel.service;

import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import com.walkhub.walkhub.domain.calorielevel.domain.repository.CalorieLevelRepository;
import com.walkhub.walkhub.domain.user.presentation.dto.response.CalorieLevelListResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.CalorieLevelListResponse.CalorieLevelResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class CalorieLevelListService {

    private final CalorieLevelRepository calorieLevelRepository;

    public CalorieLevelListResponse execute() {
        List<CalorieLevelResponse> results =
                calorieLevelRepository.findAllBy()
                        .stream()
                        .map(this::calorieLevelResponse)
                        .collect(Collectors.toList());

        return new CalorieLevelListResponse(results);
    }

    private CalorieLevelResponse calorieLevelResponse(CalorieLevel calorieLevel) {
        return CalorieLevelResponse.builder()
                .levelId(calorieLevel.getId())
                .level(calorieLevel.getLevel())
                .foodImageUrl(calorieLevel.getFoodImageUrl())
                .foodName(calorieLevel.getFoodName())
                .calorie(calorieLevel.getCalorie())
                .size(calorieLevel.getSize())
                .message(calorieLevel.getMessage())
                .build();
    }
}
