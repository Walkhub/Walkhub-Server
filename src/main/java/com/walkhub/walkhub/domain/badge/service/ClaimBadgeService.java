package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.badges.BadgeFactory;
import com.walkhub.walkhub.domain.badge.badges.BaseBadge;
import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.domain.BadgeCollection;
import com.walkhub.walkhub.domain.badge.domain.repository.BadgeCollectionRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.ClaimBadgeResponse;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.ClaimBadgeResponse.BadgeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClaimBadgeService {

    private final UserFacade userFacade;
    private final BadgeRepository badgeRepository;
    private final BadgeCollectionRepository badgeCollectionRepository;
    private final BadgeFactory badgeFactory;

    public ClaimBadgeResponse execute() {
        User user = userFacade.getCurrentUser();
        List<BadgeResponse> badgeResponses = new ArrayList<>();
        List<DefaultBadgeVO> claimBadgeList = badgeRepository.findAllByBadgeCollectionsNotIn(user.getId());

        for (DefaultBadgeVO vo : claimBadgeList) {
            BaseBadge baseBadge = badgeFactory.getBadge(vo.getCode());
            Badge badge = baseBadge.getBadgeEntity();
            if (!baseBadge.hasBadge(claimBadgeList) && baseBadge.isGoalSuccess()) {
                badgeCollectionRepository.save(
                        BadgeCollection.builder()
                                .badge(badge)
                                .user(user)
                                .build()
                );
            }
            badgeResponses.add(buildBadgeResponse(vo));
        }

        return new ClaimBadgeResponse(badgeResponses);
    }

    private BadgeResponse buildBadgeResponse(DefaultBadgeVO vo) {
        return BadgeResponse.builder()
                .badgeId(vo.getBadgeId())
                .name(vo.getName())
                .imageUrl(vo.getImageUrl())
                .build();
    }
}
