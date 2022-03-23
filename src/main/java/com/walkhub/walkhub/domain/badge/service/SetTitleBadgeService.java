package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.facade.BadgeCollectionFacade;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SetTitleBadgeService {

    private final UserFacade userFacade;
    private final BadgeCollectionFacade badgeCollectionFacade;

    @Transactional
    public void execute(Long badgeId) {
        User user = userFacade.getCurrentUser();
        Badge badge = badgeCollectionFacade.getBadgeById(badgeId, user);

        user.setBadge(badge);
    }
}
