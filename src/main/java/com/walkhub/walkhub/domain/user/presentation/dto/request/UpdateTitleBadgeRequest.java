package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateTitleBadgeRequest {

    private final String name;

    private final String imageUrl;

}