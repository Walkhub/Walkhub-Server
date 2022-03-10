package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryChallengeListResponse {

	private final List<ChallengeResponse> challengeList;

	@Getter
	@Builder
	public static class ChallengeResponse {
		private final Long challengeId;
		private final String name;
		private final LocalDate startAt;
		private final LocalDate endAt;
		private final GoalScope goalScope;
		private final GoalType goalType;
		private final Writer writer;
		private final Integer goal;
		private final String award;
	}

	@Getter
	@Builder
	public static class Writer {
		private final Long userId;
		private final String name;
		private final String profileImageUrl;
	}
}
