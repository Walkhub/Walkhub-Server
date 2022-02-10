package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.global.enums.UserScope;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueryChallengeListResponse {

	private final List<ChallengeResponse> challengeList;

	@Getter
	@Builder
	public static class ChallengeResponse {
		private final Long id;
		private final String name;
		private final LocalDateTime startAt;
		private final LocalDateTime endAt;
		private final String imageUrl;
		private final UserScope userScope;
		private final GoalScope goalScope;
		private final GoalType goalType;
		private final Writer writer;
	}

	@Getter
	@Builder
	public static class Writer {
		private final Long userId;
		private final String name;
		private final String profileImageUrl;
	}
}
