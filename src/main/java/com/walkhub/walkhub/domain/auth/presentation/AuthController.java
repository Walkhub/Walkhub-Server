package com.walkhub.walkhub.domain.auth.presentation;

import com.walkhub.walkhub.domain.auth.presentation.dto.request.CheckAccountIdRequest;
import com.walkhub.walkhub.domain.auth.presentation.dto.request.CheckAuthCodeRequest;
import com.walkhub.walkhub.domain.auth.presentation.dto.request.SignInRequest;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenRefreshResponse;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.auth.service.CheckAccountIdExistsService;
import com.walkhub.walkhub.domain.auth.service.CheckAuthCodeExistsService;
import com.walkhub.walkhub.domain.auth.service.TokenRefreshService;
import com.walkhub.walkhub.domain.auth.service.UserSignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserSignInService userSignInService;
    private final TokenRefreshService tokenRefreshService;
    private final CheckAccountIdExistsService checkAccountIdExistsService;
    private final CheckAuthCodeExistsService checkAuthCodeExistsService;

    @PostMapping("/token")
    public UserTokenResponse userSignIn(@RequestBody @Valid SignInRequest request) {
        return userSignInService.execute(request);
    }

    @PatchMapping("/token")
    public UserTokenRefreshResponse userTokenRefresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return tokenRefreshService.execute(refreshToken);
    }

    @RequestMapping(value = "/account-id", method = RequestMethod.HEAD)
    public void checkAccountIdExists(@RequestBody @Valid CheckAccountIdRequest request) {
        checkAccountIdExistsService.execute(request);
    }

    @RequestMapping(value = "/verification-codes", method = RequestMethod.HEAD)
    public void checkAuthCodeExists(@RequestBody @Valid CheckAuthCodeRequest request) {
        checkAuthCodeExistsService.execute(request);
    }
}
