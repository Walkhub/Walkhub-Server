package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.CalorieLevel;
import com.walkhub.walkhub.domain.user.domain.repository.CalorieLevelRepository;
import com.walkhub.walkhub.domain.user.presentation.dto.response.CalorieLevelListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CalorieLevelListService {

    private final CalorieLevelRepository calorieLevelRepository;

    @Transactional(readOnly = true)
    public CalorieLevelListResponse execute() {
        List<CalorieLevelListResponse.CalorieLevelResponse> results =
                calorieLevelRepository.findAllBy()
                        .stream()
                        .map(this::calorieLevelResponse)
                        .collect(Collectors.toList());

        return new CalorieLevelListResponse(results);
    }

    private CalorieLevelListResponse.CalorieLevelResponse calorieLevelResponse(CalorieLevel calorieLevel) {
        return CalorieLevelListResponse.CalorieLevelResponse.builder()
                .levelId(calorieLevel.getId())
                .foodImageUrl(calorieLevel.getFoodImageUrl())
                .foodName(calorieLevel.getFoodName())
                .calorie(calorieLevel.getCalorie())
                .size(calorieLevel.getSize())
                .message(calorieLevel.getMessage())
                .build();
    }
}
