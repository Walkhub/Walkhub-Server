package com.walkhub.walkhub.domain.badge.enums;

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
    UBD("ubd");

    private final String code;
}
