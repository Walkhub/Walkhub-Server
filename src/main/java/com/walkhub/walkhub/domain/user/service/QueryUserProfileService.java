package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse.TitleBadge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryUserProfileService {

    private final UserFacade userFacade;

    public QueryUserProfileResponse execute(Long userId) {

        User user = userFacade.getUserById(userId);
        Badge titleBadge = user.getBadge();

		return QueryUserProfileResponse.builder()
			.userId(user.getId())
			.name(user.getName())
			.profileImageUrl(user.getProfileImageUrl())
			.schoolName(user.getSchool().getName())
			.grade(user.getGroup().getGrade())
			.classNum(user.getGroup().getClassNum())
			.titleBadge(TitleBadge.builder()
				.id(titleBadge.getId())
				.name(titleBadge.getName())
				.imageUrl(titleBadge.getImageUrl())
				.build())
			.build();
	}
}
