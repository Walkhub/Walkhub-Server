package com.walkhub.walkhub.domain.excel.presentation;

import com.walkhub.walkhub.domain.excel.presentation.dto.request.PrintExcelRequest;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.PrintExcelResponse;
import com.walkhub.walkhub.domain.excel.service.PrintExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/excel")
@RestController
public class ExcelController {

    private final PrintExcelService printExcelService;

    @GetMapping
    public PrintExcelResponse getExcel(PrintExcelRequest printExcelRequest) {
        return printExcelService.execute(printExcelRequest);
    }
}
