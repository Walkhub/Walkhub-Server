package com.walkhub.walkhub.domain.challenge.presenstation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonResponse {
    private final Long userId;
    private final String name;
    private final String profileImageUrl;
}
