package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.repository.BadgeCollectionRepository;
import com.walkhub.walkhub.domain.user.exception.BadgeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BadgeCollentionFacade {

    private final BadgeCollectionRepository badgeCollectionRepository;

    public Badge getBadgeById(Long badgeId) {
        return badgeCollectionRepository.findBadgeCollectionByBadge(badgeId)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }

}
