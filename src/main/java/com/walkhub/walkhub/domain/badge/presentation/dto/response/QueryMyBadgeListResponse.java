package com.walkhub.walkhub.domain.badge.presentation.dto.response;

import com.walkhub.walkhub.domain.badge.domain.repository.vo.MyBadgeVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryMyBadgeListResponse {
    private final List<MyBadgeVo> badgeLists;
}
