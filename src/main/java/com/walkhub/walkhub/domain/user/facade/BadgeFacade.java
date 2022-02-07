package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.user.exception.BadgeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BadgeFacade {

    private final BadgeRepository badgeRepository;

    public Badge getBadgeById(Long badgeId) {

        return badgeRepository.findById(badgeId)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }
}
