package com.walkhub.walkhub.domain.auth.prsentation;

import com.walkhub.walkhub.domain.auth.prsentation.dto.request.SignInRequest;
import com.walkhub.walkhub.domain.auth.prsentation.dto.response.UserAccessTokenResponse;
import com.walkhub.walkhub.domain.auth.prsentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.auth.service.TokenRefreshService;
import com.walkhub.walkhub.domain.auth.service.UserSignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController("/users/token")
public class AuthController {

    private final UserSignInService userSignInService;
    private final TokenRefreshService tokenRefreshService;

    @PostMapping
    public UserTokenResponse userSignIn(@RequestBody @Valid SignInRequest request) {
        return userSignInService.execute(request);
    }

    @PatchMapping
    public UserAccessTokenResponse userSignIn(@RequestHeader("Refresh-Token") String refreshToken) {
        return tokenRefreshService.execute(refreshToken);
    }
}
