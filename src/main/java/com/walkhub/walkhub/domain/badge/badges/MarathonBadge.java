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
public class MarathonBadge implements BaseBadge {

    private final BadgeRepository badgeRepository;
    private final UserFacade userFacade;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    @Override
    public boolean hasBadge(List<DefaultBadgeVO> userNotOwnBadge) {
        return userNotOwnBadge.stream()
                .noneMatch(defaultBadgeVO -> defaultBadgeVO.getCode() == BadgeType.MARATHON);
    }

    @Override
    public Badge getBadgeEntity() {
        return badgeRepository.findByCode(BadgeType.MARATHON)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }

    @Override
    public boolean isGoalSuccess() {
        User user = userFacade.getCurrentUser();

        final double marathonDistance = 42.195;
        final double userDistance = exerciseAnalysisRepository.sumDistanceByUserId(user) * 100.0 * 1000.0;

        return userDistance >= marathonDistance;
    }
}
