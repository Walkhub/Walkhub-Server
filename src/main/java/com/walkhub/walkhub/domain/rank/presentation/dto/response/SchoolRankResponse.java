package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SchoolRankResponse {

	private final MySchoolResponse mySchoolRank;
	private final List<SchoolResponse> schoolList;

	@Getter
	@Builder
	public static class MySchoolResponse {
		private final Long schoolId;
		private final String name;
		private final String logoImageUrl;
		private final Integer grade;
		private final Integer classNum;
	}

	@Getter
	@Builder
	public static class SchoolResponse {
		private final Long schoolId;
		private final String name;
		private final Integer ranking;
		private final Long studentCount;
		private final String logoImageUrl;
		private final Integer walkCount;
	}
}
