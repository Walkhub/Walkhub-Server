package com.walkhub.walkhub.domain.rank.presentation;

import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
import com.walkhub.walkhub.domain.rank.service.QueryMySchoolRankService;
import com.walkhub.walkhub.domain.rank.service.QueryUserRankListByMySchoolService;
import com.walkhub.walkhub.domain.rank.service.QueryUserRankListService;
import com.walkhub.walkhub.domain.rank.service.SchoolSearchService;
import com.walkhub.walkhub.domain.rank.service.UserSearchService;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@RequestMapping("/ranks")
@RestController
public class RankController {

    private final UserSearchService userSearchService;
    private final QueryUserRankListByMySchoolService queryUserRankListByMySchoolService;
    private final QueryMySchoolRankService queryMySchoolRankService;
    private final SchoolSearchService schoolSearchService;
    private final QueryUserRankListService queryUserRankListService;

    @GetMapping("/users/search/{school-id}")
    public UserListResponse userSearch(@PathVariable("school-id") Long schoolId,
                                       @RequestParam String name,
                                       @RequestParam DateType dateType) {
        return userSearchService.execute(schoolId, name, dateType);
    }

    @GetMapping("/schools")
    public SchoolRankResponse querySchoolRank(@RequestParam("schoolDateType") SchoolDateType dateType) {
        return queryMySchoolRankService.execute(dateType);
    }

    @GetMapping("/schools/search")
    public SchoolListResponse schoolSearch(@RequestParam("name") @NotNull @Size(max = 20) String name,
                                           @RequestParam("schoolDateType") SchoolDateType dateType) {
        return schoolSearchService.execute(name, dateType);
    }

    @GetMapping("/users/my-school")
    public UserRankListResponse queryUserRankListByMySchool(@RequestParam UserRankScope scope,
                                                            @RequestParam DateType dateType) {
        return queryUserRankListByMySchoolService.execute(scope, dateType);
    }

    @GetMapping("/users/{school-id}")
    public UserRankListResponse queryUserRankList(@RequestParam DateType dateType,
                                                  @PathVariable("school-id") Long schoolId) {
        return queryUserRankListService.execute(schoolId, dateType);
    }
}
