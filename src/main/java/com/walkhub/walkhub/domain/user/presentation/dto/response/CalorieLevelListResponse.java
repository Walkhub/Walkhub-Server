package com.walkhub.walkhub.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CalorieLevelListResponse {

    private final List<CalorieLevelResponse> caloriesLevelList;

    @Getter
    @Builder
    public static class CalorieLevelResponse {
        private final Long levelId;
        private final String foodImageUrl;
        private final String foodName;
        private final Double calorie;
        private final Integer size;
        private final Integer level;
        private final String message;
    }
}
