package com.walkhub.walkhub.domain.exercise.domain.repository.vo;

import com.lannstark.ExcelColumn;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PrintExcelVo {

	@ExcelColumn(headerName = "name")
	private final String name;

	@ExcelColumn(headerName = "grade")
	private final Integer grade;

	@ExcelColumn(headerName = "classNum")
	private final Integer classNum;

	@ExcelColumn(headerName = "number")
	private final Integer number;

	@ExcelColumn(headerName = "allWalkCount")
	private final Integer allWalkCount;

	@ExcelColumn(headerName = "averageWalkCount")
	private final Integer averageWalkCount;

	@ExcelColumn(headerName = "allDistance")
	private final Integer allDistance;

	@ExcelColumn(headerName = "averageDistance")
	private final Integer averageDistance;

	@QueryProjection
	public PrintExcelVo(String name, Integer grade, Integer classNum, Integer number,
		Integer allWalkCount, Integer averageWalkCount, Integer allDistance,
		Integer averageDistance) {
		this.name = name;
		this.grade = grade;
		this.classNum = classNum;
		this.number = number;
		this.allWalkCount = allWalkCount;
		this.averageWalkCount = averageWalkCount;
		this.allDistance = allDistance;
		this.averageDistance = averageDistance;
	}
}
