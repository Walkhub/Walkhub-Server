package com.walkhub.walkhub.domain.rank.presentation;

import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.domain.rank.presentation.dto.request.SchoolSearchRequest;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse.MySchoolResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListByMySchoolResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
import com.walkhub.walkhub.domain.rank.service.*;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("/ranks")
@RestController
public class RankController {

    private final UserSearchService userSearchService;
    private final QueryUserRankListByMySchoolService queryUserRankListByMySchoolService;
    private final QueryMySchoolRankService queryMySchoolRankService;
    private final SchoolSearchService schoolSearchService;
    private final QueryUserRankListService queryUserRankListService;

    @GetMapping("/schools")
    public MySchoolResponse querySchoolRank() {
        return queryMySchoolRankService.execute();
    }

    @GetMapping("/schools/search")
    public SchoolListResponse schoolSearch(@Valid SchoolSearchRequest request) {
        return schoolSearchService.execute(request);
    }

    @GetMapping("/users/{school-id}")
    public UserRankListResponse queryUserRankList(@PathVariable("school-id") Long schoolId,
                                                  @RequestParam DateType dateType) {
        return queryUserRankListService.execute(schoolId, dateType);
    }

    @GetMapping("/users/my-school")
    public UserRankListByMySchoolResponse queryUserRankListByMySchool(@RequestParam UserRankScope scope,
                                                            @RequestParam DateType dateType) {
        return queryUserRankListByMySchoolService.execute(scope, dateType);
    }

    @GetMapping("/users/search/{school-id}")
    public UserListResponse userSearch(@PathVariable("school-id") Long schoolId,
                                       @RequestParam String name,
                                       @RequestParam DateType dateType) {
        return userSearchService.execute(schoolId, name, dateType);
    }

}
