package com.walkhub.walkhub.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryMyPageResponse {

	private final Long id;
	private final String name;
	private final String profileImageUrl;
	private final String schoolName;
	private final Integer grade;
	private final TitleBadge titleBadge;

	@Getter
	@Builder
	public static class TitleBadge {
		private final Long id;
		private final String name;
		private final String imageUrl;
	}
}
