package com.walkhub.walkhub.domain.badge.badges;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import com.walkhub.walkhub.domain.badge.exception.BadgeNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BetaTasterBadge implements BaseBadge {

    private final BadgeRepository badgeRepository;

    @Override
    public Boolean hasBadge(List<DefaultBadgeVO> userOwnBadge) {
        return userOwnBadge.stream()
                .anyMatch(defaultBadgeVO -> defaultBadgeVO.getCode() == BadgeType.BETA_TESTER);
    }

    @Override
    public Badge getBadgeEntity() {
        return badgeRepository.findByCode(BadgeType.NEWBIE)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }

    @Override
    public Boolean isGoalSuccess() {
        return true;
    }
}
