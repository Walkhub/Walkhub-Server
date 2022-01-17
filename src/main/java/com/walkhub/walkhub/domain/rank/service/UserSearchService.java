package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.UserRank;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserSearchResponse;
import com.walkhub.walkhub.global.enums.Scope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserSearchService {

    private final UserRankRepository userRankRepository;

    @Transactional(readOnly = true)
    public UserListResponse execute(String name, Scope scope, String agencyCode, Integer grade, Integer classNum) {
        List<UserRank> userRankList;

        if (Scope.CLS.equals(scope)) {
            userRankList = userRankRepository.findTop100ByNameContainsAndAgencyCodeAndClassNumAndGrade(name, agencyCode, grade, classNum);
        } else {
            userRankList = userRankRepository.findTop100ByNameContainsAndAgencyCode(name, agencyCode);
        }

        List<UserSearchResponse> userList = userRankList.stream().map(userRank ->
                UserSearchResponse.builder()
                        .accountId(userRank.getAccountId())
                        .classNum(userRank.getClassNum())
                        .grade(userRank.getGrade())
                        .name(userRank.getName())
                        .profileImageUrl(userRank.getProfileImageUrl())
                        .walkCount(userRank.getClassNum())
                        .build()
        ).collect(Collectors.toList());

        return UserListResponse.builder()
                .userList(userList).build();

    }
}
