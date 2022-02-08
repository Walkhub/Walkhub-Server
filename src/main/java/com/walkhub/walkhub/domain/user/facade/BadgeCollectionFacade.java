package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.BadgeCollection;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.BadgeCollectionRepository;
import com.walkhub.walkhub.domain.user.exception.BadgeNotFoundException;
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
