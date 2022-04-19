package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRankListByMySchoolResponse {

	private final Boolean isJoinedClass;
	private final UserRankResponse myRanking;
	private final List<UserRankResponse> rankList;

	@Getter
	@Builder
	public static class UserRankResponse {
		private final Long userId;
		private final String name;
		private final Integer ranking;
		private final String profileImageUrl;
		private final Integer walkCount;
		private final Boolean isMeasuring;
	}
}
