package com.walkhub.walkhub.domain.badge.domain.repository;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRepository extends CrudRepository<Badge, Long> {
    List<Badge> findAll(Long badgeId);
}
