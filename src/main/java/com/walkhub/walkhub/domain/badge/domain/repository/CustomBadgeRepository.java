package com.walkhub.walkhub.domain.badge.domain.repository;

import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.MyBadgeVo;

import java.util.List;

public interface CustomBadgeRepository {
    List<DefaultBadgeVO> findAllByBadgeCollectionsNotIn(Long userId);
    List<MyBadgeVo> findAllByBadgeCollections(Long userId);
}
