package com.walkhub.walkhub.domain.exercise.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.Getter;

@Getter
public class PrintExcelVo {

	private final String name;
	private final Integer grade;
	private final Integer classNum;
	private final Integer number;
	private final Integer allWalkCount;
	private final Integer averageWalkCount;
	private final Integer allDistance;
	private final Integer averageDistance;
	private final Authority authority;
	private final String schoolName;

	@QueryProjection
	public PrintExcelVo(String name, Integer grade, Integer classNum, Integer number,
		Integer allWalkCount, Integer averageWalkCount, Integer allDistance, Integer averageDistance, Authority authority, String schoolName) {
		this.name = name;
		this.grade = grade;
		this.classNum = classNum;
		this.number = number;
		this.allWalkCount = allWalkCount;
		this.averageWalkCount = averageWalkCount;
		this.allDistance = allDistance;
		this.averageDistance = averageDistance;
		this.authority = authority;
		this.schoolName = schoolName;
	}
}
