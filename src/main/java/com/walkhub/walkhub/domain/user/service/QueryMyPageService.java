package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.CalorieLevel;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse.Level;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse.TitleBadge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryMyPageService {

    private final UserFacade userFacade;

    public QueryUserProfileResponse execute() {
        User user = userFacade.getCurrentUser();
        Badge titleBadge = user.getBadge();
        CalorieLevel level = user.getMaxLevel();
        return QueryUserProfileResponse.builder()
            .userId(user.getId())
            .name(user.getName())
            .profileImageUrl(user.getProfileImageUrl())
            .schoolName(user.getSchool().getName())
            .grade(user.getGroup().getGrade())
            .classNum(user.getNumber())
            .titleBadge(TitleBadge.builder()
                .id(titleBadge.getId())
                .name(titleBadge.getName())
                .imageUrl(titleBadge.getImageUrl())
                .build())
            .level(Level.builder()
                .image_url(level.getFoodImageUrl())
                .name(level.getFoodName())
                .build())
            .build();
    }
}
