package com.walkhub.walkhub.domain.calorielevel.service;

import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import com.walkhub.walkhub.domain.calorielevel.domain.repository.CalorieLevelRepository;
import com.walkhub.walkhub.domain.user.presentation.dto.response.CalorieLevelListResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.CalorieLevelListResponse.CalorieLevelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
        List<CalorieLevelResponse> results =
                calorieLevelRepository.findAllBy(Sort.by(Sort.Direction.ASC, "level"))
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
