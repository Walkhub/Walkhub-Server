package com.walkhub.walkhub.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListOfBadgesOwnResponse {

    private final Long id;
    private final String name;
    private final String image;

}
