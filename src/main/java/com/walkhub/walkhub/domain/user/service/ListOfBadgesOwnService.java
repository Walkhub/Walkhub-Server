package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.facade.BadgeFacade;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.ListOfBadgesOwnResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ListOfBadgesOwnService {

    private final UserFacade userFacade;
    private final BadgeFacade badgeFacade;

    @Transactional(readOnly = true)
    public ListOfBadgesOwnResponse execute(Long userId, Long badgeId) {

        Badge badge = badgeFacade.getBadgeId(badgeId);
        userFacade.getUserById(userId);

        return ListOfBadgesOwnResponse.builder()
                .id(badge.getId())
                .name(badge.getName())
                .image(badge.getImage())
                .build();
    }

}

