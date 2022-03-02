package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchoolListResponse {

	private final List<SchoolResponse> schoolList;

	@Getter
	@Builder
	public static class SchoolResponse {
		private final Long schoolId;
		private final String schoolName;
		private final Integer ranking;
		private final String logoImageUrl;
		private final Integer walkCount;
	}
}
