package com.walkhub.walkhub.domain.excel.presentation.dto.request;

import com.walkhub.walkhub.global.enums.Authority;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PrintExcelRequest {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endAt;

	private Authority authority;

	private Integer grade;

	private Integer classNum;
}
