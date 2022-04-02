package com.walkhub.walkhub.domain.su.presentation;

import com.walkhub.walkhub.domain.su.presentation.dto.response.RootAccountResponse;
import com.walkhub.walkhub.domain.su.service.CreateRootAccountService;
import com.walkhub.walkhub.domain.su.service.UpdateSchoolPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/su")
@RestController
public class SuController {

    private final CreateRootAccountService createRootAccountService;
    private final UpdateSchoolPasswordService updateSchoolPasswordService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/accounts/{school-id}")
    public RootAccountResponse rootAccount(@PathVariable("school-id") Long schoolId) {
        return createRootAccountService.execute(schoolId);
    }

    @PatchMapping("/accounts/{school-id}")
    public RootAccountResponse updateRootPassword(@PathVariable("school-id") Long schoolId) {
        return updateSchoolPasswordService.execute(schoolId);
    }
}
