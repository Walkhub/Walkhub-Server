package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.MyBadgeVo;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryMyBadgeListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryMyBadgeListService {

    private final BadgeRepository badgeRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryMyBadgeListResponse execute() {
        User user = userFacade.getCurrentUser();
        List<MyBadgeVo> badgeVOList = badgeRepository.findAllByBadgeCollections(user.getId());

        return new QueryMyBadgeListResponse(badgeVOList.stream()
                .map(myBadgeVo ->
                        QueryMyBadgeListResponse.MyBadge.builder()
                                .badgeId(myBadgeVo.getBadgeId())
                                .name(myBadgeVo.getName())
                                .imageUrl(myBadgeVo.getImageUrl())
                                .code(myBadgeVo.getCode())
                                .isMine(myBadgeVo.getIsMine())
                                .condition(myBadgeVo.getCondition())
                                .build()
                ).collect(Collectors.toList()));
    }
}
