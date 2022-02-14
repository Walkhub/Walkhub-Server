package com.walkhub.walkhub.domain.calorielevel.presentation;

import com.walkhub.walkhub.domain.calorielevel.service.CalorieLevelListService;
import com.walkhub.walkhub.domain.calorielevel.service.UpdateMaxCalorieLevelService;
import com.walkhub.walkhub.domain.user.presentation.dto.response.CalorieLevelListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/levels")
@RestController
public class CalorieLevelController {

    private final CalorieLevelListService calorieLevelListService;
    private final UpdateMaxCalorieLevelService updateMaxCalorieLevelService;

    @GetMapping("/lists")
    public CalorieLevelListResponse calorieLevelList() {
        return calorieLevelListService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{level-id}")
    public void updateMaxCalorieLevel(@PathVariable("level-id") Long levelId) {
        updateMaxCalorieLevelService.execute(levelId);
    }

}
