package com.walkhub.walkhub.domain.challenge.presentation.dto.response;

import com.walkhub.walkhub.global.enums.Scope;
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

		private final Scope scope;
	}
}
