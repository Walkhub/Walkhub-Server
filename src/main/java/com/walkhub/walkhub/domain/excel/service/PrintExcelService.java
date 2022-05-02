package com.walkhub.walkhub.domain.excel.service;

import com.walkhub.walkhub.domain.excel.presentation.dto.request.PrintExcelRequest;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.PrintExcelResponse;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.PrintExcelResponse.PrintExcelVo;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class PrintExcelService {

    private final ExerciseAnalysisRepository exerciseAnalysisRepository;
    private final UserFacade userFacade;

    public PrintExcelResponse execute(PrintExcelRequest printExcelRequest) {
        User user = userFacade.getCurrentUser();

        List<PrintExcelVo> printExcelVoList =
                exerciseAnalysisRepository.getPrintExcelVoList(printExcelRequest, user.getUserSchoolId());

        return new PrintExcelResponse(printExcelVoList);
    }
}
