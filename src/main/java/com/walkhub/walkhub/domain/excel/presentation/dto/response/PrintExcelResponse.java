package com.walkhub.walkhub.domain.excel.presentation.dto.response;

import com.walkhub.walkhub.domain.exercise.domain.repository.vo.PrintExcelVo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PrintExcelResponse {

	private final List<PrintExcelVo> userList;
}
