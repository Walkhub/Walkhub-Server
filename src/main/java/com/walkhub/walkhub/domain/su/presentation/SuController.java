package com.walkhub.walkhub.domain.su.presentation;

import com.walkhub.walkhub.domain.su.presentation.dto.response.CreateRootAccountResponse;
import com.walkhub.walkhub.domain.su.presentation.dto.response.SchoolListResponse;
import com.walkhub.walkhub.domain.su.service.CreateRootAccountService;
import com.walkhub.walkhub.domain.su.service.ShowSchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/su")
@RestController
public class SuController {

    private final ShowSchoolService showSchoolService;
    private final CreateRootAccountService createRootAccountService;

    @GetMapping
    public SchoolListResponse showSchool(Pageable page) {
        return showSchoolService.execute(page);
    }

    @PostMapping("/accounts/{school-id}")
    public CreateRootAccountResponse rootAccount(@PathVariable("school-id") Long schoolId) {
        return createRootAccountService.execute(schoolId);
    }
}
