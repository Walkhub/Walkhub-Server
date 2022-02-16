package com.walkhub.walkhub.domain.auth.presentation;

import com.walkhub.walkhub.domain.auth.presentation.dto.request.CheckAccountIdRequest;
import com.walkhub.walkhub.domain.auth.presentation.dto.request.SignInRequest;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserAccessTokenResponse;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.auth.service.CheckAccountIdExistsService;
import com.walkhub.walkhub.domain.auth.service.TokenRefreshService;
import com.walkhub.walkhub.domain.auth.service.UserSignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserSignInService userSignInService;
    private final TokenRefreshService tokenRefreshService;
    private final CheckAccountIdExistsService checkAccountIdExistsService;

    @PostMapping("/token")
    public UserTokenResponse userSignIn(@RequestBody @Valid SignInRequest request) {
        return userSignInService.execute(request);
    }

    @PatchMapping("/token")
    public UserAccessTokenResponse userTokenRefresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return tokenRefreshService.execute(refreshToken);
    }

    @RequestMapping(value = "/account-id", method = RequestMethod.HEAD)
    public void checkAccountIdExists(@RequestBody @Valid CheckAccountIdRequest request) {
        checkAccountIdExistsService.execute(request);
    }
}
