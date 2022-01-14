package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.ListOfBadgesOwnResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ListOfBadgesOwnService {

    private final BadgeRepository badgeRepository;
    private final UserFacade userFacade;


    public ListOfBadgesOwnResponse execute(Badge badge) {
        User user = userFacade.getCurrentUser();
        List<Badge> badges = badgeRepository.findBadgesById(badge).stream()
                .map(this::getBadge)
                .collect(toList());

        return new ListOfBadgesOwnResponse(user, badges);

    }

    private ListOfBadgesOwnResponse getBadge(Badge badge) {
        return ListOfBadgesOwnResponse.builder()
                .id(badge.getId())
                .image(badge.getImage())
                .name(badge.getName())
                .build();
    }
}
