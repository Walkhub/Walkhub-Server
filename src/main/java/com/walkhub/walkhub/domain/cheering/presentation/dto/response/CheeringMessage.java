package com.walkhub.walkhub.domain.cheering.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CheeringMessage {
    @JsonProperty("user_name")
    private final String userName;

    @JsonProperty("user_id")
    private final Long userId;
}