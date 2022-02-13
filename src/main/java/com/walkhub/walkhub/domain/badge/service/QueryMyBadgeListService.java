package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryMyBadgeListResponse;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.QueryMyBadgeListResponse.BadgeListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<BadgeListResponse> badgeListResponseList = new ArrayList<>();
        List<DefaultBadgeVO> badgeVOList = badgeRepository.findAllByBadgeCollections(user.getId())
                .stream()
                .map(defaultBadgeVO -> DefaultBadgeVO.builder()
                        .badgeId(defaultBadgeVO.getBadgeId())
                        .name(defaultBadgeVO.getName())
                        .imageUrl(defaultBadgeVO.getImageUrl())
                        .build()
                )
                .collect(Collectors.toList());

        return new QueryMyBadgeListResponse(badgeListResponseList);
    }
}
