package com.walkhub.walkhub.domain.badge.presentation;

import com.walkhub.walkhub.domain.badge.presentation.dto.response.ClaimBadgeResponse;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryMyBadgeListResponse;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryUserBadgeListResponse;
import com.walkhub.walkhub.domain.badge.service.ClaimBadgeService;
import com.walkhub.walkhub.domain.badge.service.QueryMyBadgeListService;
import com.walkhub.walkhub.domain.badge.service.QueryUserBadgeListService;
import com.walkhub.walkhub.domain.badge.service.SetTitleBadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/badges")
@RestController
public class BadgeController {

    private final SetTitleBadgeService setTitleBadgeService;
    private final QueryUserBadgeListService queryUserBadgeListService;
    private final ClaimBadgeService claimBadgeService;
    private final QueryMyBadgeListService myBadgeListService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{badge-id}")
    public void setTitleBadge(@PathVariable("badge-id") Long badgeId) {
        setTitleBadgeService.execute(badgeId);
    }

    @GetMapping("/{user-id}")
    public QueryUserBadgeListResponse queryUserBadgeList(@PathVariable(name = "user-id") Long userId) {
        return queryUserBadgeListService.execute(userId);
    }
  
    @GetMapping("/new")
    public ClaimBadgeResponse claimBadge() {
        return claimBadgeService.execute();
    }

    @GetMapping
    public QueryMyBadgeListResponse myBadgeList() {
        return myBadgeListService.execute();
    }

}
