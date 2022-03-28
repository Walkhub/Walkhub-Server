package com.walkhub.walkhub.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryMyInformationResponse {
    private String name;
    private String profileImageUrl;
    private String schoolName;
    private Integer grade;
    private Integer classNum;
}
