package com.walkhub.walkhub.domain.badge.presentation.dto.response;

import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryMyBadgeListResponse {

    private final List<DefaultBadgeVO> badgeLists;

    @Getter
    @Builder
    public static class BadgeListResponse {
        private final Long id;
        private final String name;
        private final String imageUrl;
        private final boolean isMine;
    }
}
