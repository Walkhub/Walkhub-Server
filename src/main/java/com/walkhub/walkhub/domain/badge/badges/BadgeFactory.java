package com.walkhub.walkhub.domain.badge.badges;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import com.walkhub.walkhub.domain.badge.exception.BadgeTypeNotFoundException;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BadgeFactory {

    private final BadgeRepository badgeRepository;
    private final UserFacade userFacade;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    public BaseBadge getBadge(BadgeType badgeType) {
        switch (badgeType) {
            case NEWBIE:
                return new StartBadge(badgeRepository);
            case BETA_TESTER:
                return new BetaTesterBadge(badgeRepository);
            case DAY_10000_STEPS:
                return new Day10000StepsBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case DAY_30000_STEPS:
                return new Day30000StepsBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case DAY_50000_STEPS:
                return new Day50000StepsBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case SLIVER_SHOES:
                return new SliverShoesBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case GOLD_SHOES:
                return new GoldShoesBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case MARATHON:
                return new MarathonBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            default:
                throw BadgeTypeNotFoundException.EXCEPTION;
        }
    }
}
