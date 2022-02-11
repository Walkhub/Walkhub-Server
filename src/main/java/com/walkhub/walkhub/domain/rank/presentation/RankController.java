package com.walkhub.walkhub.domain.rank.presentation;

import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
import com.walkhub.walkhub.domain.rank.service.QueryUserRankListService;
import com.walkhub.walkhub.domain.rank.service.UserSearchService;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/ranks")
@RestController
public class RankController {

    private final UserSearchService userSearchService;
    private final QueryUserRankListService queryUserRankListService;

    @GetMapping("/users/search")
    public UserListResponse userSearch(@RequestParam String name,
                                       @RequestParam UserScope userScope,
                                       @RequestParam String agencyCode,
                                       @RequestParam(required = false) Integer grade,
                                       @RequestParam(required = false) Integer classNum) {
        return userSearchService.execute(name, userScope, agencyCode, grade, classNum);
    }

    @GetMapping("/users/my-school")
    public UserRankListResponse queryUserRankListByMySchool(@RequestParam String scope, @RequestParam String dateType) {
        return queryUserRankListService.execute(scope, dateType);
    }
}
