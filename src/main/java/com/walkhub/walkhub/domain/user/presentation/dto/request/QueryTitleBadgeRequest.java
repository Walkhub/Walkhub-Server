package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QueryTitleBadgeRequest {

    private Long badgeId;

    private String name;

    private String image;
}
