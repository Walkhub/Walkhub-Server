package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeCollectionRepository;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryUserBadgeListResponse;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryUserBadgeListResponse.DefaultBadgeResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryUserBadgeListService {

    private final BadgeCollectionRepository badgeCollectionRepository;

    public QueryUserBadgeListResponse execute(Long userId) {

        List<DefaultBadgeResponse> badgeList = badgeCollectionRepository.findAllByUserId(userId)
                .stream()
                .map(badgeCollection -> DefaultBadgeResponse.builder()
                        .badgeId(badgeCollection.getBadge().getId())
                        .name(badgeCollection.getBadge().getName())
                        .imageUrl(badgeCollection.getBadge().getImageUrl())
                        .build())
                .collect(Collectors.toList());

        return new QueryUserBadgeListResponse(badgeList);
    }
}
