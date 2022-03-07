package com.walkhub.walkhub.domain.badge.badges;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;

import java.util.List;

public interface BaseBadge {
    boolean hasBadge(List<DefaultBadgeVO> userNotOwnBadge);

    Badge getBadgeEntity();

    boolean isGoalSuccess();
}
