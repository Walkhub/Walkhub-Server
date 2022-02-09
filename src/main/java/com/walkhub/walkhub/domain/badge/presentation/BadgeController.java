package com.walkhub.walkhub.domain.badge.presentation;

import com.walkhub.walkhub.domain.badge.service.TitleBadgeSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/badges")
@RestController
public class BadgeController {

    private final TitleBadgeSettingService titleBadgeSettingService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{badge-id}")
    public void titleBadgeSetting(@PathVariable("badge-id") Long badgeId) {
        titleBadgeSettingService.execute(badgeId);
    }

}
