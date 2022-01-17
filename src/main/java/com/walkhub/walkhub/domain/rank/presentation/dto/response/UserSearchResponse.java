package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSearchResponse {

    private String accountId;
    private String name;
    private Integer rank;
    private Integer grade;
    private Integer classNum;
    private String profileImageUrl;
    private Integer walkCount;
}
