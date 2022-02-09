package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.user.domain.BadgeCollection;
import com.walkhub.walkhub.domain.user.domain.BadgeCollectionId;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BadgeCollectionRepository extends CrudRepository<BadgeCollection, BadgeCollectionId> {

    Optional<BadgeCollection> findByBadgeIdAndUser(Long badgeId, User user);
}
