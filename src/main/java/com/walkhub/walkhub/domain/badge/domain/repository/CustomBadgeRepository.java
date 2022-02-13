package com.walkhub.walkhub.domain.badge.domain.repository;

import com.walkhub.walkhub.domain.badge.domain.repository.vo.ClaimBadgeVO;

import java.util.List;

public interface CustomBadgeRepository {
    List<ClaimBadgeVO> findAllByBadgeCollectionsNotIn(Long userId);
}
