package com.walkhub.walkhub.domain.user.presentation;

import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.request.*;
import com.walkhub.walkhub.domain.user.presentation.dto.response.CalorieLevelListResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryUserProfileResponse;
import com.walkhub.walkhub.domain.user.presentation.dto.response.UserAccountIdResponse;
import com.walkhub.walkhub.domain.user.service.*;
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
    private final JoinSectionService joinSectionService;
    private final UpdatePasswordService updatePasswordService;
    private final UpdateSchoolInfoService updateSchoolInfoService;
    private final SearchAccountIdService searchAccountIdService;
    private final CalorieLevelListService calorieLevelListService;
    private final UpdateGoalWalkCountService updateGoalWalkCountService;

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
    @PostMapping("/classes/{section-id}")
    public void joinSection(@PathVariable(name = "section-id") Long sectionId,
                            @RequestBody @Valid JoinSectionRequest request) {
        joinSectionService.execute(sectionId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/school")
    public void updateSchoolInfo(@RequestBody @Valid UpdateSchoolInfoRequest request) {
        updateSchoolInfoService.execute(request);
    }

    @GetMapping("/accounts/{phone-number}")
    public UserAccountIdResponse searchAccountId(@PathVariable(name = "phone-number") String phoneNumber) {
        return searchAccountIdService.execute(phoneNumber);
    }

    @GetMapping("/levels/lists")
    public CalorieLevelListResponse calorieLevelList() {
        return calorieLevelListService.execute();
    }

    @PostMapping("/goal")
    public void updateGoalWalk(@RequestBody @Valid UpdateGoalWalkCountRequest request){
        updateGoalWalkCountService.execute(request);
    }
}
