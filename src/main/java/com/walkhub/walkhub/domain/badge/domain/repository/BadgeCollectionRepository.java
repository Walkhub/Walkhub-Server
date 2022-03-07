package com.walkhub.walkhub.domain.badge.domain.repository;

import com.walkhub.walkhub.domain.badge.domain.BadgeCollection;
import com.walkhub.walkhub.domain.badge.domain.BadgeCollectionId;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BadgeCollectionRepository extends CrudRepository<BadgeCollection, BadgeCollectionId> {
    List<BadgeCollection> findAllByUserId(Long userId);

    Optional<BadgeCollection> findByBadgeIdAndUser(Long badgeId, User user);
}
