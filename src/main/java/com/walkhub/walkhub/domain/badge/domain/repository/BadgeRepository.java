package com.walkhub.walkhub.domain.badge.domain.repository;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BadgeRepository extends CrudRepository<Badge, Long>, CustomBadgeRepository {
    Optional<Badge> findByCode(BadgeType code);
}
