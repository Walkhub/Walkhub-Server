package com.walkhub.walkhub.domain.rank.presentation;

import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
import com.walkhub.walkhub.domain.rank.service.QueryUserRankListService;
import com.walkhub.walkhub.domain.rank.service.QuerySchoolRankService;
import com.walkhub.walkhub.domain.rank.service.UserSearchService;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/ranks")
@RestController
public class RankController {

    private final UserSearchService userSearchService;
    private final QueryUserRankListService queryUserRankListService;
    private final QuerySchoolRankService querySchoolRankService;

    @GetMapping("/users/search/{school-id}")
    public UserListResponse userSearch(@PathVariable("school-id") Long schoolId,
                                       @RequestParam String name,
                                       @RequestParam DateType dateType) {
        return userSearchService.execute(schoolId, name, dateType);
    }

    @GetMapping("/schools")
    public SchoolRankResponse querySchoolRank(@RequestParam SchoolDateType dateType) {
        return querySchoolRankService.execute(dateType);
    }

    @GetMapping("/users/my-school")
    public UserRankListResponse queryUserRankListByMySchool(@RequestParam String scope, @RequestParam String dateType) {
        return queryUserRankListService.execute(scope, dateType);
    }
}
