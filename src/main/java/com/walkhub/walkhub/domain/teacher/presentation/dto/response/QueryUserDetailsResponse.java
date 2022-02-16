package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryUserDetailsResponse {
	private final Long userId;
	private final String name;
	private final String profileImageUrl;
	private final Integer grade;
	private final Integer classNum;
	private final Integer number;
	private final List<Integer> walkCountList;
	private final List<ExerciseResponse> exerciseList;

	@Getter
	@Builder
	public static class ExerciseResponse {
		private final String imageUrl;
		private final LocalDateTime startAt;
		private final BigDecimal latitude;
		private final BigDecimal longitude;
	}
}
