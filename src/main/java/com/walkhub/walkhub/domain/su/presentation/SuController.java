package com.walkhub.walkhub.domain.su.presentation;

import com.walkhub.walkhub.domain.su.presentation.dto.response.CreateRootAccountResponse;
import com.walkhub.walkhub.domain.su.presentation.dto.response.SchoolListResponse;
import com.walkhub.walkhub.domain.su.presentation.dto.response.UpdateSchoolPasswordResponse;
import com.walkhub.walkhub.domain.su.service.CreateRootAccountService;
import com.walkhub.walkhub.domain.su.service.ShowSchoolService;
import com.walkhub.walkhub.domain.su.service.UpdateSchoolPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/su")
@RestController
public class SuController {

    private final ShowSchoolService showSchoolService;
    private final CreateRootAccountService createRootAccountService;
    private final UpdateSchoolPasswordService updateSchoolPasswordService;

    @GetMapping
    public SchoolListResponse showSchool(Pageable page) {
        return showSchoolService.execute(page);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/accounts/{school-id}")
    public CreateRootAccountResponse rootAccount(@PathVariable("school-id") Long schoolId) {
        return createRootAccountService.execute(schoolId);
    }

    @PatchMapping("/accounts/{school-id}")
    public UpdateSchoolPasswordResponse updateRootPassword(@PathVariable("school-id") Long schoolId) {
        return updateSchoolPasswordService.execute(schoolId);
    }
}
