package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.BadgeFacade;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TitleBadgeSettingService {

    private final UserFacade userFacade;
    private final BadgeFacade badgeFacade;

    @Transactional
    public void execute(Long badgeId) {
        User user = userFacade.getCurrentUser();
        Badge badge = badgeFacade.getBadgeById(badgeId);

        user.setBadge(badge);
    }
}
