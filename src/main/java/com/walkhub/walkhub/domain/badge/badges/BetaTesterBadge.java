package com.walkhub.walkhub.domain.badge.badges;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import com.walkhub.walkhub.domain.badge.exception.BadgeNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BetaTesterBadge implements BaseBadge {

    private final BadgeRepository badgeRepository;

    @Override
    public boolean hasBadge(List<DefaultBadgeVO> userNotOwnBadge) {
        return userNotOwnBadge.stream()
                .noneMatch(defaultBadgeVO -> defaultBadgeVO.getCode() == BadgeType.BETA_TESTER);
    }

    @Override
    public Badge getBadgeEntity() {
        return badgeRepository.findByCode(BadgeType.BETA_TESTER)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }

    @Override
    public boolean isGoalSuccess() {
        return true;
    }
}
