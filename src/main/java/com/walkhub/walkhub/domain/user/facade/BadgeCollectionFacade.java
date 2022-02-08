package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.repository.BadgeCollectionRepository;
import com.walkhub.walkhub.domain.user.exception.BadgeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BadgeCollectionFacade {

    private final BadgeCollectionRepository badgeCollectionRepository;

    public Badge getBadgeById(Long badgeId, Long userId) {
        return badgeCollectionRepository.findBadgeCollectionByBadgeAndUser(badgeId, userId)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }

}
