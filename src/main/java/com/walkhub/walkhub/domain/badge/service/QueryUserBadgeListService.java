package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryUserBadgeListResponse;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryUserBadgeListResponse.DefaultBadgeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryUserBadgeListService {

    private final BadgeRepository badgeRepository;
    private final UserFacade userFacade;

    public QueryUserBadgeListResponse execute(Long badgeId) {

        List<DefaultBadgeResponse> badgeList = badgeRepository.findAll(badgeId)
                .stream()
                .map(badge -> DefaultBadgeResponse.builder()
                        .name(badge.getName())
                        .imageUrl(badge.getImageUrl())
                        .condition(badge.getCondition())
                        .build())
                .collect(Collectors.toList());

        return new QueryUserBadgeListResponse(badgeList);
    }
}
