package com.walkhub.walkhub.domain.user.presentation;

import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.request.CheckClassCodeRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.request.InputHealthInformationRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.request.JoinSectionRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateGoalWalkCountRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdatePasswordRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UserAuthCodeRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UserSignUpRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryGoalWalkCountResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.UserAccountIdResponse;
import com.walkhub.walkhub.domain.user.service.CheckClassCodeService;
import com.walkhub.walkhub.domain.user.service.ExitSectionService;
import com.walkhub.walkhub.domain.user.service.InputHealthInformationService;
import com.walkhub.walkhub.domain.user.service.JoinSectionService;
import com.walkhub.walkhub.domain.user.service.QueryGoalWalkCountService;
import com.walkhub.walkhub.domain.user.service.QueryMyPageService;
import com.walkhub.walkhub.domain.user.service.QueryUserProfileService;
import com.walkhub.walkhub.domain.user.service.SearchAccountIdService;
import com.walkhub.walkhub.domain.user.service.UpdateGoalWalkCountService;
import com.walkhub.walkhub.domain.user.service.UpdatePasswordService;
import com.walkhub.walkhub.domain.user.service.UpdateUserInfoService;
import com.walkhub.walkhub.domain.user.service.UserAuthCodeService;
import com.walkhub.walkhub.domain.user.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserAuthCodeService userAuthCodeService;
    private final QueryMyPageService queryMyPageService;
    private final QueryUserProfileService queryUserProfileService;
    private final UserSignUpService userSignUpService;
    private final InputHealthInformationService inputHealthInformationService;
    private final UpdateUserInfoService updateUserInfoService;
    private final JoinSectionService joinSectionService;
    private final UpdatePasswordService updatePasswordService;
    private final SearchAccountIdService searchAccountIdService;
    private final UpdateGoalWalkCountService updateGoalWalkCountService;
    private final ExitSectionService exitSectionService;
    private final QueryGoalWalkCountService queryGoalWalkCountService;
    private final CheckClassCodeService checkClassCodeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/verification-codes")
    public void sendAuthCode(@RequestBody @Valid UserAuthCodeRequest request) {
        userAuthCodeService.execute(request);
    }

    @GetMapping
    public QueryUserProfileResponse queryMyPage() {
        return queryMyPageService.execute();
    }

    @GetMapping("/{user-id}")
    public QueryUserProfileResponse queryUserProfile(@PathVariable("user-id") Long userId) {
        return queryUserProfileService.execute(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserTokenResponse userSignUp(@RequestBody @Valid UserSignUpRequest request) {
        return userSignUpService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/health")
    public void inputHealthInformationRequest(@RequestBody @Valid InputHealthInformationRequest request) {
        inputHealthInformationService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateUserInfo(@RequestBody @Valid UpdateUserInfoRequest request) {
        updateUserInfoService.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/classes")
    public void joinSection(@RequestBody @Valid JoinSectionRequest request) {
        joinSectionService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @GetMapping("/accounts/{phone-number}")
    public UserAccountIdResponse searchAccountId(@PathVariable(name = "phone-number") String phoneNumber) {
        return searchAccountIdService.execute(phoneNumber);
    }

    @GetMapping("/goal")
    public QueryGoalWalkCountResponse queryGaolWalkCount() {
        return queryGoalWalkCountService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/goal")
    public void updateGoalWalkCount(@RequestBody @Valid UpdateGoalWalkCountRequest updateGoalWalkCountRequest) {
        updateGoalWalkCountService.execute(updateGoalWalkCountRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/classes")
    public void exitSection() {
        exitSectionService.execute();
    }

    @RequestMapping(value = "/classes", method = RequestMethod.HEAD)
    public void checkClassCode(@RequestBody @Valid CheckClassCodeRequest request) {
        checkClassCodeService.execute(request.getCode());
    }

}
