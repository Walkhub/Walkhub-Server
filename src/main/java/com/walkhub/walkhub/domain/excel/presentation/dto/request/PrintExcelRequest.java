package com.walkhub.walkhub.domain.excel.presentation.dto.request;

import com.walkhub.walkhub.domain.excel.domain.type.UserType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class PrintExcelRequest {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endAt;

	private UserType userType;

	private Integer grade;

	private Integer classNum;
}
