package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BadgeRepository extends CrudRepository<Badge, Long> {
    Optional<Badge> findByBadgeId(Long badgeId);
}
