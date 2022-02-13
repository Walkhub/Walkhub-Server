package com.walkhub.walkhub.domain.calorielevel.presentation;

import com.walkhub.walkhub.domain.calorielevel.service.CalorieLevelListService;
import com.walkhub.walkhub.domain.user.presentation.dto.response.CalorieLevelListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/levels")
@RestController
public class CalorieLevelController {

    private final CalorieLevelListService calorieLevelListService;

    @GetMapping("/levels/lists")
    public CalorieLevelListResponse calorieLevelList() {
        return calorieLevelListService.execute();
    }

}
