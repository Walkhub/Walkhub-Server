package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryMyPageResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryMyPageResponse.TitleBadge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryMyPageService {

	private final UserFacade userFacade;

	public QueryMyPageResponse execute() {
		User user = userFacade.getCurrentUser();
		Badge titleBadge = user.getBadge();
		return QueryMyPageResponse.builder()
			.id(user.getId())
			.name(user.getName())
			.profileImage(user.getProfileImageUrl())
			.schoolName(user.getSchool().getName())
			.grade(user.getGroup().getGrade())
			.titleBadge(TitleBadge.builder()
				.id(titleBadge.getId())
				.name(titleBadge.getName())
				.image(titleBadge.getImage())
				.build())
			.build();
	}
}
