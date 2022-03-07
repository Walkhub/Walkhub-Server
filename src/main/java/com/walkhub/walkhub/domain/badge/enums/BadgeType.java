package com.walkhub.walkhub.domain.badge.enums;

import com.walkhub.walkhub.domain.badge.badges.BaseBadge;
import com.walkhub.walkhub.domain.badge.badges.BetaTesterBadge;
import com.walkhub.walkhub.domain.badge.badges.Day10000StepsBadge;
import com.walkhub.walkhub.domain.badge.badges.Day30000StepsBadge;
import com.walkhub.walkhub.domain.badge.badges.Day50000StepsBadge;
import com.walkhub.walkhub.domain.badge.badges.FirstPlaceBadge;
import com.walkhub.walkhub.domain.badge.badges.FrozenHumanBadge;
import com.walkhub.walkhub.domain.badge.badges.GoJinHoBadge;
import com.walkhub.walkhub.domain.badge.badges.GoldShoesBadge;
import com.walkhub.walkhub.domain.badge.badges.MarathonBadge;
import com.walkhub.walkhub.domain.badge.badges.SeoulBusanBadge;
import com.walkhub.walkhub.domain.badge.badges.SliverShoesBadge;
import com.walkhub.walkhub.domain.badge.badges.StartBadge;
import com.walkhub.walkhub.domain.badge.badges.TenFirstPlaceBadge;
import com.walkhub.walkhub.domain.badge.badges.UbdBadge;
import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.exception.BadgeTypeNotFoundException;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BadgeType {

    NEWBIE("newbie"),
    BETA_TESTER("beta_tester"),
    DAY_10000_STEPS("day_10000_steps"),
    DAY_30000_STEPS("day_30000_steps"),
    DAY_50000_STEPS("day_50000_steps"),
    FROZEN_HUMAN("frozen_human"),
    GO_JIN_HO("go_jin_ho"),
    SLIVER_SHOES("sliver_shoes"),
    GOLD_SHOES("gold_shoes"),
    MARATHON("marathon"),
    UBD("ubd"),
    SEOUL_BUSAN("seoul_busan"),
    FIRST_PLACE("first_place"),
    TEN_FIRST_PLACE("ten_first_place"),
    RECEIVE_10000("receive_10000");

    private final String code;

    public static BaseBadge getBadge(BadgeRepository badgeRepository, UserFacade userFacade,
                                     ExerciseAnalysisRepository exerciseAnalysisRepository,
                                     UserRankRepository userRankRepository, BadgeType badgeType) {
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
            case FROZEN_HUMAN:
                return new FrozenHumanBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case GO_JIN_HO:
                return new GoJinHoBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case SLIVER_SHOES:
                return new SliverShoesBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case GOLD_SHOES:
                return new GoldShoesBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case MARATHON:
                return new MarathonBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case UBD:
                return new UbdBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case SEOUL_BUSAN:
                return new SeoulBusanBadge(badgeRepository, userFacade, exerciseAnalysisRepository);
            case FIRST_PLACE:
                return new FirstPlaceBadge(badgeRepository, userFacade, userRankRepository);
            case TEN_FIRST_PLACE:
                return new TenFirstPlaceBadge(badgeRepository, userFacade, userRankRepository);
            case RECEIVE_10000:

            default:
                throw BadgeTypeNotFoundException.EXCEPTION;
        }
    }
}
