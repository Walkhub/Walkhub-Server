package com.walkhub.walkhub.domain.challenge.presentation.dto.response;

import com.walkhub.walkhub.global.enums.Scope;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChallengeResponse {

    private final Long id;

    private final String name;

    private final String imageUrl;

    private final String content;

    private final LocalDateTime createAt;

    private final LocalDateTime endAt;

    private final String award;

    private final Scope scope;

    private final Long goal;

}
