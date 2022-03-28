package com.walkhub.walkhub.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryMyInformationResponse {
    private final String name;
    private final String profileImageUrl;
    private final String schoolName;
    private final Integer grade;
    private final Integer classNum;
}
