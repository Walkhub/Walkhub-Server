package com.walkhub.walkhub.domain.teacher.presentation;

import com.walkhub.walkhub.domain.teacher.presentation.dto.request.CreateClassRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.TeacherCodeRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.CodeResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserListResponse;
import com.walkhub.walkhub.domain.teacher.service.*;
import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.DetailsClassResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserDetailsResponse;
import com.walkhub.walkhub.domain.teacher.service.ConfirmTeacherCodeService;
import com.walkhub.walkhub.domain.teacher.service.CreateClassService;
import com.walkhub.walkhub.domain.teacher.service.DeleteClassService;
import com.walkhub.walkhub.domain.teacher.service.QueryStudentCodeService;
import com.walkhub.walkhub.domain.teacher.service.QueryUserDetailsService;
import com.walkhub.walkhub.domain.teacher.service.RefreshClassCodeService;
import com.walkhub.walkhub.domain.teacher.service.VerificationCodeService;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/teachers")
@RestController
public class TeacherController {

    private final CreateClassService createClassService;
    private final VerificationCodeService verificationCodeService;
    private final DeleteClassService deleteClassService;
    private final RefreshClassCodeService refreshClassCodeService;
    private final QueryUserListService queryUserListService;
    private final QueryStudentCodeService queryStudentCodeService;
    private final QueryUserDetailsService queryUserDetailsService;
    private final ConfirmTeacherCodeService confirmTeacherCodeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/classes")
    public CodeResponse createClass(@RequestBody @Valid CreateClassRequest request) {
        return createClassService.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/verification-codes")
    public CodeResponse verificationCode() {
        return verificationCodeService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/classes/{section-id}")
    public void deleteClass(@PathVariable(name = "section-id") Long sectionId) {
        deleteClassService.execute(sectionId);
    }

    @PatchMapping("/classes/verification-codes")
    public CodeResponse refreshClassCode() {
        return refreshClassCodeService.execute();
    }

    @GetMapping("/users")
    public QueryUserListResponse queryUserList(@RequestParam Integer page,
                                               @RequestParam AuthorityScope scope,
                                               @RequestParam SortStandard sort,
                                               @RequestParam(required = false) Integer grade,
                                               @RequestParam(required = false) Integer classNum) {
        return queryUserListService.execute(page, scope, sort, grade, classNum);
    }

    @GetMapping("/classes")
    public DetailsClassResponse queryStudentCode() {
        return queryStudentCodeService.execute();
    }

    @GetMapping("/users/{user-id}")
    public QueryUserDetailsResponse queryUserDetails(@PathVariable Long userId,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt) {
        return queryUserDetailsService.execute(userId, startAt, endAt);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/verification-codes")
    public void confirmTeacherCode(@RequestBody TeacherCodeRequest teacherCodeRequest) {
        confirmTeacherCodeService.execute(teacherCodeRequest);
    }
}
