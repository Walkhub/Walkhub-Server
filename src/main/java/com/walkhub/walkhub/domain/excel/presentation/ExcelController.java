package com.walkhub.walkhub.domain.excel.presentation;

import com.walkhub.walkhub.domain.excel.presentation.dto.request.UserInfoExcelRequest;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.UserInfoExcelResponse;
import com.walkhub.walkhub.domain.excel.service.UserInfoExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/excel")
@RestController
public class ExcelController {

    private final UserInfoExcelService userInfoExcelService;

    @GetMapping
    public UserInfoExcelResponse getUserInfoExcel(UserInfoExcelRequest request) {
        return userInfoExcelService.execute(request);
    }
}
