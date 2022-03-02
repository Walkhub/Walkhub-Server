package com.walkhub.walkhub.domain.challenge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class RelatedChallengeParticipantsVO {
    private final Long userId;
    private final String name;
    private final String profileImageUrl;

    @QueryProjection
    public RelatedChallengeParticipantsVO(Long userId, String name, String profileImageUrl) {
        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}
