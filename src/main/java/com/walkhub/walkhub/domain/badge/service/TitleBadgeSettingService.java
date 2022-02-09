package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.BadgeCollectionFacade;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TitleBadgeSettingService {

    private final UserFacade userFacade;
    private final BadgeCollectionFacade badgeCollectionFacade;

    @Transactional
    public void execute(Long badgeId) {
        User user = userFacade.getCurrentUser();
        Badge badge = badgeCollectionFacade.getBadgeById(badgeId, user);

        user.setBadge(badge);
    }
}
