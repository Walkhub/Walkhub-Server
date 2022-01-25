package com.walkhub.walkhub.domain.school.presentation;

import com.walkhub.walkhub.domain.school.presentation.dto.request.SchoolLogoRequest;
import com.walkhub.walkhub.domain.school.service.SchoolLogoSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/schools")
@RestController
public class SchoolController {

    private final SchoolLogoSettingService schoolLogoSettingService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/logos/{agency-code}")
    public void schoolLogoSetting(@PathVariable(name = "agency-code") String agencyCode,
                                  @RequestBody @Valid SchoolLogoRequest request) {
        schoolLogoSettingService.execute(agencyCode, request);
    }
}
