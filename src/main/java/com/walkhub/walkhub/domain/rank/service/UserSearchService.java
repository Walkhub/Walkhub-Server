package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.UserRankInfo;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserListResponse;
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
        List<UserRankInfo> userRankInfoList;

        if (Scope.CLS.equals(scope)) {
            userRankInfoList = userRankRepository.findTop100ByNameContainsAndAgencyCodeAndClassNumAndGrade(name, agencyCode, grade, classNum);
        } else {
            userRankInfoList = userRankRepository.findTop100ByNameContainsAndAgencyCode(name, agencyCode);
        }

        List<UserListResponse.UserSearchResponse> userList = userRankInfoList.stream().map(userRankInfo ->
                UserListResponse.UserSearchResponse.builder()
                        .accountId(userRankInfo.getAccountId())
                        .name(userRankInfo.getName())
                        .rank(userRankInfo.getRanking())
                        .grade(userRankInfo.getGrade())
                        .classNum(userRankInfo.getClassNum())
                        .profileImageUrl(userRankInfo.getProfileImageUrl())
                        .walkCount(userRankInfo.getClassNum())
                        .build()
        ).collect(Collectors.toList());

        return UserListResponse.builder()
                .userList(userList).build();

    }
}
