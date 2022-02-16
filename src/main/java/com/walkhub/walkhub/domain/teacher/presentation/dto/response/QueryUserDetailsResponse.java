package com.walkhub.walkhub.domain.teacher.presentation.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

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
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:SS")
		private final LocalDateTime startAt;
		private final BigDecimal latitude;
		private final BigDecimal longitude;
	}
}
