package com.walkhub.walkhub.domain.excel.service;

import com.walkhub.walkhub.domain.excel.presentation.dto.request.PrintExcelRequest;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.PrintExcelResponse;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.PrintExcelResponse.PrintExcelVo;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PrintExcelService {

	private final ExerciseAnalysisRepository exerciseAnalysisRepository;
	private final UserFacade userFacade;

	@Transactional(readOnly = true)
	public PrintExcelResponse execute(PrintExcelRequest printExcelRequest) {
		Long schoolId = userFacade.getCurrentUser().getSchool().getId();
		List<PrintExcelVo> printExcelVoList = exerciseAnalysisRepository.getPrintExcelVoList(printExcelRequest, schoolId);

		return new PrintExcelResponse(printExcelVoList);
	}
}
