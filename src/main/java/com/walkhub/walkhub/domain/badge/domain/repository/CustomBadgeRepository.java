package com.walkhub.walkhub.domain.badge.domain.repository;

import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;

import java.util.List;

public interface CustomBadgeRepository {
    List<DefaultBadgeVO> findAllByBadgeCollectionsNotIn(Long userId);
    List<DefaultBadgeVO> findAllByBadgeCollections(Long userId);
}
