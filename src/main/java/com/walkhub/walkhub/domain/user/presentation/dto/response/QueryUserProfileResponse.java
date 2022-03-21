package com.walkhub.walkhub.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryUserProfileResponse {

	private final Long userId;
	private final String name;
	private final String profileImageUrl;
	private final Long schoolId;
	private final String schoolName;
	private final String schoolImageUrl;
	private final Integer grade;
	private final Integer classNum;
	private final Integer dailyWalkCountGoal;
	private final TitleBadge titleBadge;
	private final Level level;

	@Getter
	@Builder
	public static class TitleBadge {
		private final Long badgeId;
		private final String name;
		private final String imageUrl;
	}

	@Getter
	@Builder
	public static class Level {
		private final Long levelId;
		private final String name;
		private final String imageUrl;
	}
}
