package com.walkhub.walkhub.domain.teacher.presentation;

import com.walkhub.walkhub.domain.teacher.presentation.dto.request.CreateClassRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.QueryUserListRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.TeacherCodeRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.UpdateTeacherSchoolRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.UserSearchRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.*;
import com.walkhub.walkhub.domain.teacher.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/teachers")
@RestController
public class TeacherController {

    private final CreateClassService createClassService;
    private final GenerateVerificationCodeService generateVerificationCodeService;
    private final DeleteClassService deleteClassService;
    private final QueryUserListService queryUserListService;
    private final DetailsClassService detailsClassService;
    private final QueryUserDetailsService queryUserDetailsService;
    private final ConfirmTeacherCodeService confirmTeacherCodeService;
    private final ClassListService classListService;
    private final UpdateTeacherSchoolService updateTeacherSchoolService;
    private final UserSearchForTeacherService userSearchForTeacherService;
    private final QueryMyClassService queryMyClassService;
    private final ManageTeacherService manageTeacherService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/classes")
    public CodeResponse createClass(@RequestBody @Valid CreateClassRequest request) {
        return createClassService.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/verification-codes")
    public CodeResponse verificationCode() {
        return generateVerificationCodeService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/classes/{section-id}")
    public void deleteClass(@PathVariable(name = "section-id") Long sectionId) {
        deleteClassService.execute(sectionId);
    }

    @GetMapping("/users")
    public QueryUserListResponse queryUserList(QueryUserListRequest request) {
        return queryUserListService.execute(request);
    }

    @GetMapping("/classes/{section-id}")
    public DetailsClassResponse detailsClass(@PathVariable("section-id") Long sectionId) {
        return detailsClassService.execute(sectionId);
    }

    @GetMapping("/users/{user-id}")
    public QueryUserDetailsResponse queryUserDetails(@PathVariable("user-id") Long userId,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt) {
        return queryUserDetailsService.execute(userId, startAt, endAt);
    }

    @PatchMapping("/verification-codes")
    public TokenResponse confirmTeacherCode(@RequestBody TeacherCodeRequest teacherCodeRequest) {
        return confirmTeacherCodeService.execute(teacherCodeRequest);
    }

    @GetMapping("/classes/lists")
    public ClassListResponse classList() {
        return classListService.execute();
    }

    @PatchMapping("/schools")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTeacherSchool(@Valid @RequestBody UpdateTeacherSchoolRequest request) {
        updateTeacherSchoolService.execute(request);
    }

    @GetMapping("/users/search")
    public QueryUserListResponse searchUser(UserSearchRequest request) {
        return userSearchForTeacherService.execute(request);
    }

    @GetMapping("/my-class")
    public ClassResponse queryMyClass() {
        return queryMyClassService.execute();
    }

    @GetMapping("/lists")
    public ManageTeachersResponse manageTeachers() {
        return manageTeacherService.execute();
    }

}
