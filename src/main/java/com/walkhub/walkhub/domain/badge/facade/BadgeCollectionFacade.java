package com.walkhub.walkhub.domain.badge.facade;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.domain.BadgeCollection;
import com.walkhub.walkhub.domain.badge.domain.repository.BadgeCollectionRepository;
import com.walkhub.walkhub.domain.badge.exception.BadgeNotFoundException;
import com.walkhub.walkhub.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BadgeCollectionFacade {

    private final BadgeCollectionRepository badgeCollectionRepository;

    public Badge getBadgeById(Long badgeId, User user) {
        return badgeCollectionRepository.findByBadgeIdAndUser(badgeId, user)
                .map(BadgeCollection::getBadge)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }

}
