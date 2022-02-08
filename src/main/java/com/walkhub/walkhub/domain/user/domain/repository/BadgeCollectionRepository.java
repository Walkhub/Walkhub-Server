package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.BadgeCollection;
import com.walkhub.walkhub.domain.user.domain.BadgeCollectionId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BadgeCollectionRepository extends CrudRepository<BadgeCollection, BadgeCollectionId> {

    Optional<Badge> findByBadgeAndUser(Long badgeId);
}
