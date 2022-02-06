package com.walkhub.walkhub.domain.cheering.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheerRequest {
    @JsonProperty("user_id")
    private Long userId;
}
