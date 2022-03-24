package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse.Level;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse.TitleBadge;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryMyPageService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryUserProfileResponse execute() {
        User user = userFacade.getCurrentUser();
        Badge titleBadge = user.getBadge();
        CalorieLevel level = user.getMaxLevel();
        School school = user.getSchool();
        Section section = user.hasSection() ? user.getSection() : Section.builder().build();

        return QueryUserProfileResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .schoolId(school.getId())
                .schoolName(school.getName())
                .schoolImageUrl(school.getLogoImageUrl())
                .classNum(section.getClassNum())
                .grade(section.getGrade())
                .dailyWalkCountGoal(user.getDailyWalkCountGoal())
                .titleBadge(TitleBadge.builder()
                        .badgeId(titleBadge.getId())
                        .name(titleBadge.getName())
                        .imageUrl(titleBadge.getImageUrl())
                        .build())
                .level(Level.builder()
                        .levelId(level.getId())
                        .imageUrl(level.getFoodImageUrl())
                        .name(level.getFoodName())
                        .build())
                .build();
    }
}