package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.MyBadgeVo;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryMyBadgeListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryMyBadgeListService {

    private final BadgeRepository badgeRepository;
    private final UserFacade userFacade;

    public QueryMyBadgeListResponse execute() {
        User user = userFacade.getCurrentUser();
        List<MyBadgeVo> badgeVOList = badgeRepository.findAllByBadgeCollections(user.getId());

        return new QueryMyBadgeListResponse(badgeVOList.stream()
                .map(myBadgeVo ->
                        QueryMyBadgeListResponse.MyBadge.builder()
                                .badgeId(myBadgeVo.getBadgeId())
                                .name(myBadgeVo.getName())
                                .imageUrl(myBadgeVo.getImageUrl())
                                .isMine(myBadgeVo.getIsMine())
                                .condition(myBadgeVo.getCondition())
                                .build()
                ).collect(Collectors.toList()));
    }
}
