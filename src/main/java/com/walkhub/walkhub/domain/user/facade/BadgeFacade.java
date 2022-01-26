package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BadgeFacade {

    private final BadgeRepository badgeRepository;

    public Badge getBadgeId(Long badgeId) {
        return badgeRepository.findByBadgeId(badgeId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
