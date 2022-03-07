package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeCollectionRepository;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryUserBadgeListResponse;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryUserBadgeListResponse.DefaultBadgeResponse;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@WalkhubService
public class QueryUserBadgeListService {

    private final BadgeCollectionRepository badgeCollectionRepository;

    public QueryUserBadgeListResponse execute(Long userId) {

        List<DefaultBadgeResponse> badgeList = badgeCollectionRepository.findAllByUserId(userId)
                .stream()
                .map(badgeCollection -> DefaultBadgeResponse.builder()
                        .name(badgeCollection.getBadge().getName())
                        .imageUrl(badgeCollection.getBadge().getImageUrl())
                        .condition(badgeCollection.getBadge().getUnlockCondition())
                        .code(badgeCollection.getBadge().getCode())
                        .build())
                .collect(Collectors.toList());

        return new QueryUserBadgeListResponse(badgeList);
    }
}
