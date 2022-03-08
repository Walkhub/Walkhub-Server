package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse.Level;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse.TitleBadge;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@WalkhubService
public class QueryUserProfileService {

    private final UserFacade userFacade;

    public QueryUserProfileResponse execute(Long userId) {

        User user = userFacade.getUserById(userId);
        Badge titleBadge = user.getBadge();
		CalorieLevel level = user.getMaxLevel();
		Section section = user.hasSection() ? user.getSection() : Section.builder().build();

		return QueryUserProfileResponse.builder()
			.userId(user.getId())
			.name(user.getName())
			.profileImageUrl(user.getProfileImageUrl())
			.schoolName(user.getSchool().getName())
			.classNum(section.getClassNum())
			.grade(section.getGrade())
			.titleBadge(TitleBadge.builder()
				.id(titleBadge.getId())
				.name(titleBadge.getName())
				.imageUrl(titleBadge.getImageUrl())
				.build())
			.level(Level.builder()
				.imageUrl(level.getFoodImageUrl())
				.name(level.getFoodName())
				.build())
			.build();
	}
}
