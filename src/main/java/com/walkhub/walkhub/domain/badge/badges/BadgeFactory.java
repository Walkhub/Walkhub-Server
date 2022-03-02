package com.walkhub.walkhub.domain.badge.badges;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BadgeFactory {

    private final BadgeRepository badgeRepository;

    public BaseBadge getBadge(BadgeType badgeType) {
        switch (badgeType) {
            case NEWBIE:
                return new StartBadge(badgeRepository);
            default:
                return null;
        }
    }
}
