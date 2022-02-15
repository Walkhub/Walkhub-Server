package com.walkhub.walkhub.domain.excel.presentation.dto.request;

import com.walkhub.walkhub.domain.excel.domain.type.UserType;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PrintExcelRequest {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endAt;

	private UserType userType;

	private Integer grade;

	private Integer classNum;
}
