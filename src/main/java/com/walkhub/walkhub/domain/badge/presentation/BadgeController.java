package com.walkhub.walkhub.domain.badge.presentation;

import com.walkhub.walkhub.domain.badge.service.SetTitleBadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/badges")
@RestController
public class BadgeController {

    private final SetTitleBadgeService setTitleBadgeService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{badge-id}")
    public void setTitleBadge(@PathVariable("badge-id") Long badgeId) {
        setTitleBadgeService.execute(badgeId);
    }

}
