package com.walkhub.walkhub.domain.rank.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class SchoolListVo {

	private final Long schoolId;
	private final String schoolName;
	private final Integer ranking;
	private final String logoImageUrl;
	private final Integer walkCount;
	private final Long userCount;

	@QueryProjection
	public SchoolListVo(Long schoolId, String schoolName, Integer ranking,
		String logoImageUrl, Integer walkCount, Long userCount) {
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.ranking = ranking;
		this.logoImageUrl = logoImageUrl;
		this.walkCount = walkCount;
		this.userCount = userCount;
	}
}
