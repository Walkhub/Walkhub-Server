package com.walkhub.walkhub.domain.excel.presentation.dto.request;

import com.walkhub.walkhub.domain.excel.domain.type.UserType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrintExcelRequest {

	private LocalDate startAt;

	private LocalDate endAt;

	private UserType userType;

	private Integer grade;

	private Integer classNum;
}
