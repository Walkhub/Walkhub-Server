package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeCollectionRepository;
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

    private final BadgeCollectionRepository badgeCollectionRepository;
    private final UserFacade userFacade;

    public QueryUserBadgeListResponse execute(Long userId) {

        User user = userFacade.getCurrentUser();
        List<DefaultBadgeResponse> badgeList = badgeCollectionRepository.findByUserId(userId)
                .stream()
                .map(badgeCollection -> DefaultBadgeResponse.builder()
                        .name(badgeCollection.getBadge().getName())
                        .imageUrl(badgeCollection.getBadge().getImageUrl())
                        .condition(badgeCollection.getBadge().getCondition())
                        .build())
                .collect(Collectors.toList());

        return new QueryUserBadgeListResponse(badgeList);
    }
}
