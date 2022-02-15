package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.exercise.domain.repository.vo.PrintExcelVo;
import com.walkhub.walkhub.global.enums.Authority;
import java.time.LocalDate;
import java.util.List;

public interface ExerciseAnalysisRepositoryCustom {
	List<PrintExcelVo> getPrintExcelVoList(LocalDate startAt, LocalDate endAt, Authority authority, Integer grade, Integer classNum, Long SchoolId);
}
