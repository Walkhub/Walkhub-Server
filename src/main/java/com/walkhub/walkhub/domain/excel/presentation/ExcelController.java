package com.walkhub.walkhub.domain.excel.presentation;

import com.walkhub.walkhub.domain.excel.service.PrintExcelService;
import com.walkhub.walkhub.global.enums.Authority;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/excel")
@RestController
public class ExcelController {

	private final PrintExcelService printExcelService;

	@GetMapping
	public void getExcel(@RequestParam("startAt") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
		@RequestParam("endAt") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt, @RequestParam("authority") Authority authority,
		@RequestParam(value = "grade", required = false) Integer grade, @RequestParam(value = "classNum", required = false) Integer classNum,
		HttpServletResponse response) throws IOException {
		printExcelService.execute(startAt, endAt, authority, grade, classNum, response);
	}
}
