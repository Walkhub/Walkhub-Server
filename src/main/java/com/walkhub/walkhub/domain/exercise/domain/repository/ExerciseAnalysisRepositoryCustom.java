package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.excel.presentation.dto.request.PrintExcelRequest;
import com.walkhub.walkhub.domain.exercise.domain.repository.vo.PrintExcelVo;
import java.util.List;

public interface ExerciseAnalysisRepositoryCustom {
	List<PrintExcelVo> getPrintExcelVoList(PrintExcelRequest printExcelRequest, Long schoolId);
}
