package com.walkhub.walkhub.domain.auth.presentation;

import com.walkhub.walkhub.domain.auth.presentation.dto.request.CheckAuthCodeRequest;
import com.walkhub.walkhub.domain.auth.presentation.dto.request.SignInRequest;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.AuthUserInfoResponse;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenRefreshResponse;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.auth.service.AuthUserInfoService;
import com.walkhub.walkhub.domain.auth.service.CheckAccountIdExistsService;
import com.walkhub.walkhub.domain.auth.service.CheckAuthCodeExistsService;
import com.walkhub.walkhub.domain.auth.service.TokenRefreshService;
import com.walkhub.walkhub.domain.auth.service.UserSignInService;
import com.walkhub.walkhub.domain.user.service.CheckClassCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserSignInService userSignInService;
    private final TokenRefreshService tokenRefreshService;
    private final CheckAccountIdExistsService checkAccountIdExistsService;
    private final CheckAuthCodeExistsService checkAuthCodeExistsService;
    private final AuthUserInfoService authUserInfoService;
    private final CheckClassCodeService checkClassCodeService;

    @PostMapping("/token")
    public UserTokenResponse userSignIn(@RequestBody @Valid SignInRequest request) {
        return userSignInService.execute(request);
    }

    @PatchMapping("/token")
    public UserTokenRefreshResponse userTokenRefresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return tokenRefreshService.execute(refreshToken);
    }

    @RequestMapping(value = "/account-id", method = RequestMethod.HEAD)
    public void checkAccountIdExists(@Valid @NotBlank @RequestParam(name = "accountId") String accountId) {
        checkAccountIdExistsService.execute(accountId);
    }

    @RequestMapping(value = "/verification-codes", method = RequestMethod.HEAD)
    public void checkAuthCodeExists(@Valid CheckAuthCodeRequest request) {
        checkAuthCodeExistsService.execute(request);
    }

    @RequestMapping(value = "/classes", method = RequestMethod.HEAD)
    public void checkClassCode(@Valid @NotBlank @RequestParam(name = "code") String code) {
        checkClassCodeService.execute(code);
    }

    @GetMapping("/auth/info")
    public AuthUserInfoResponse authUserInfo() {
        return authUserInfoService.execute();
    }
}
