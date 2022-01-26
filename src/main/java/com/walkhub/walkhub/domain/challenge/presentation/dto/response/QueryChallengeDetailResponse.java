package com.walkhub.walkhub.domain.challenge.presentation.dto.response;

import com.walkhub.walkhub.global.enums.Scope;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryChallengeDetailResponse {
	private final String name;
	private final String content;
	private final Long goal;
	private final String award;
	private final String imageUrl;
	private final LocalDateTime startAt;
	private final LocalDateTime endAt;
	private final Scope scope;
	private final Long participantCount;
	private final Boolean isMine;
	private final Writer writer;

	@Getter
	@Builder
	public static class Writer{
		private final Long id;
		private final String name;
		private final String profileImageUrl;
	}
}
