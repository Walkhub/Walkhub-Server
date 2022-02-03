package com.walkhub.walkhub.domain.rank.presentation;

import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserListResponse;
import com.walkhub.walkhub.domain.rank.service.UserSearchService;
import com.walkhub.walkhub.global.enums.Scope;
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

    @GetMapping("/users/search")
    public UserListResponse userSearch(@RequestParam String name,
                                       @RequestParam Scope scope,
                                       @RequestParam String agencyCode,
                                       @RequestParam(required = false) Integer grade,
                                       @RequestParam(required = false) Integer classNum) {
        return userSearchService.execute(name, scope, agencyCode, grade, classNum);
    }
}
