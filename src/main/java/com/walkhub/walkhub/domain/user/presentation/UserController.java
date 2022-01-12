package com.walkhub.walkhub.domain.user.presentation;

import com.walkhub.walkhub.domain.auth.prsentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.request.InputHealthInformationRequest;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UserAuthCodeRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryMyPageResponse;
import com.walkhub.walkhub.domain.user.service.*;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UserSignUpRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse;

import com.walkhub.walkhub.domain.user.service.QueryUserProfileService;
import com.walkhub.walkhub.domain.user.service.UserAuthCodeService;
import com.walkhub.walkhub.domain.user.service.UserSignUpService;
import com.walkhub.walkhub.domain.user.service.UpdateUserInfoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/verification-codes")
    public void sendAuthCode(@RequestBody @Valid UserAuthCodeRequest request) {
        userAuthCodeService.execute(request);
    }
  
    @GetMapping
    public QueryMyPageResponse queryMyPage() {
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

    @PatchMapping("/health")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inputHealthInformationRequest(@RequestBody @Valid InputHealthInformationRequest request) {
        inputHealthInformationService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateUserInfo(@RequestBody @Valid UpdateUserInfoRequest request) {
        updateUserInfoService.execute(request);
    }
}
