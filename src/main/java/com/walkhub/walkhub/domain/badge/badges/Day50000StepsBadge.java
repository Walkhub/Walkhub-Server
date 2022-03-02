package com.walkhub.walkhub.domain.badge.badges;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import com.walkhub.walkhub.domain.badge.exception.BadgeNotFoundException;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Day50000StepsBadge implements BaseBadge {

    private final BadgeRepository badgeRepository;
    private final UserFacade userFacade;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    @Override
    public boolean hasBadge(List<DefaultBadgeVO> userOwnBadge) {
        return userOwnBadge.stream()
                .anyMatch(defaultBadgeVO -> defaultBadgeVO.getCode() == BadgeType.DAY_50000_STEPS);
    }

    @Override
    public Badge getBadgeEntity() {
        return badgeRepository.findByCode(BadgeType.DAY_50000_STEPS)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }

    @Override
    public boolean isGoalSuccess() {
        User user = userFacade.getCurrentUser();

        Integer walkCount =
                exerciseAnalysisRepository.countAllByUserAndWalkCountGreaterThanEqual(user, 50000);

        return walkCount >= 1;
    }
}
