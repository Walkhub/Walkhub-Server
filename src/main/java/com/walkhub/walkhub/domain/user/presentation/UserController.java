package com.walkhub.walkhub.domain.user.presentation;

import com.walkhub.walkhub.domain.user.presentation.dto.request.UserAuthCodeRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryMyPageResponse;
import com.walkhub.walkhub.domain.user.service.QueryMyPageService;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse;
import com.walkhub.walkhub.domain.user.service.QueryUserProfileService;
import com.walkhub.walkhub.domain.user.service.UserAuthCodeService;
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

}
