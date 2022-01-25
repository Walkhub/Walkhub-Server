package com.walkhub.walkhub.domain.teacher.presentation;

import com.walkhub.walkhub.domain.teacher.presentation.dto.request.CreateClassRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.CreateClassResponse;
import com.walkhub.walkhub.domain.teacher.service.CreateClassService;
import com.walkhub.walkhub.domain.teacher.service.DeleteClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/teachers")
@RestController
public class TeacherController {

    private final CreateClassService createClassService;
    private final DeleteClassService deleteClassService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/classes")
    public CreateClassResponse createClass(@RequestBody @Valid CreateClassRequest request) {
        return createClassService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/classes/{agency-code}/{grade}/{class}")
    public void deleteClass(@PathVariable(name = "agency-code") String agencyCode,
                            @PathVariable(name = "grade") Integer grade,
                            @PathVariable(name = "class") Integer classNum) {
        deleteClassService.execute(agencyCode, grade, classNum);
    }

}
