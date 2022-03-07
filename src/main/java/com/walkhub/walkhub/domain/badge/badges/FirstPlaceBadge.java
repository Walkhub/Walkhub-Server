package com.walkhub.walkhub.domain.badge.badges;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import com.walkhub.walkhub.domain.badge.exception.BadgeNotFoundException;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FirstPlaceBadge implements BaseBadge {

    private final BadgeRepository badgeRepository;
    private final UserFacade userFacade;
    private final UserRankRepository userRankRepository;

    @Override
    public boolean hasBadge(List<DefaultBadgeVO> userNotOwnBadge) {
        return userNotOwnBadge.stream()
                .noneMatch(defaultBadgeVO -> defaultBadgeVO.getCode() == BadgeType.FIRST_PLACE);
    }

    @Override
    public Badge getBadgeEntity() {
        return badgeRepository.findByCode(BadgeType.FIRST_PLACE)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }

    @Override
    public boolean isGoalSuccess() {
        User user = userFacade.getCurrentUser();

        Integer count = userRankRepository.countAllByUserIdAndRankingAndDateType(user.getId(), 1, DateType.DAY);

        return count >= 1;
    }
}
