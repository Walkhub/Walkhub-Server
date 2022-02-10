package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.CalorieLevel;
import com.walkhub.walkhub.domain.user.domain.repository.CalorieLevelRepository;
import com.walkhub.walkhub.domain.user.presentation.dto.response.CalorieLevelListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CalorieLevelListService {

    private final CalorieLevelRepository calorieLevelRepository;

    public CalorieLevelListResponse execute() {
        List<CalorieLevelListResponse.CalorieLevelResponse> results =
                calorieLevelRepository.findAllBy()
                        .stream()
                        .map(this::BuildCalorieLevelList)
                        .collect(Collectors.toList());

        return new CalorieLevelListResponse(results);
    }

    private CalorieLevelListResponse.CalorieLevelResponse BuildCalorieLevelList(CalorieLevel calorieLevel) {
        return CalorieLevelListResponse.CalorieLevelResponse.builder()
                .foodImageUrl(calorieLevel.getFoodImageUrl())
                .foodName(calorieLevel.getFoodName())
                .calorie(calorieLevel.getCalorie())
                .size(calorieLevel.getSize())
                .message(calorieLevel.getMessage())
                .build();
    }
}
