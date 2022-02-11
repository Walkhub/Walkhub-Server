package com.walkhub.walkhub.domain.school.presentation;

import com.walkhub.walkhub.domain.school.presentation.dto.request.SchoolLogoRequest;
import com.walkhub.walkhub.domain.school.presentation.dto.response.SearchSchoolListResponse;
import com.walkhub.walkhub.domain.school.service.SchoolLogoSettingService;
import com.walkhub.walkhub.domain.school.service.SearchSchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/schools")
@RestController
public class SchoolController {

    private final SchoolLogoSettingService schoolLogoSettingService;
    private final SearchSchoolService searchSchoolService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/logos/{school-id}")
    public void schoolLogoSetting(@PathVariable(name = "school-id") Long schoolId,
                                  @RequestBody @Valid SchoolLogoRequest request) {
        schoolLogoSettingService.execute(schoolId, request);
    }

    @GetMapping("/search")
    public SearchSchoolListResponse searchSchool(@RequestParam String name) {
        return searchSchoolService.execute(name);
    }
}
