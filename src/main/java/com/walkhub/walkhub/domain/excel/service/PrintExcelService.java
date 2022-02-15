package com.walkhub.walkhub.domain.excel.service;

import com.lannstark.excel.ExcelFile;
import com.lannstark.excel.onesheet.OneSheetExcelFile;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.vo.PrintExcelVo;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PrintExcelService {

	private final ExerciseAnalysisRepository exerciseAnalysisRepository;
	private final UserFacade userFacade;

	@Transactional(readOnly = true)
	public void execute(LocalDate startAt, LocalDate endAt, Authority authority, Integer grade, Integer classNum, HttpServletResponse response)
		throws IOException {
		Long schoolId = userFacade.getCurrentUser().getSchool().getId();
		List<PrintExcelVo> printExcelVoList = exerciseAnalysisRepository.getPrintExcelVoList(startAt, endAt, authority, grade, classNum, schoolId);

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		ExcelFile excelFile = new OneSheetExcelFile<>(printExcelVoList, PrintExcelVo.class);
		excelFile.write(response.getOutputStream());
	}
}
