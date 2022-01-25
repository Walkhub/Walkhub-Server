package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.ListOfBadgesOwnResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ListOfBadgesOwnService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public ListOfBadgesOwnResponse execute(Long userId) {

        User user = userFacade.getUserById(userId);
        Badge badge = user.getBadge();
        return ListOfBadgesOwnResponse.builder()
                .name(user.getName())
                .image(user.getProfileImageUrl())
                .build();
    }

}

