package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserListResponse {

    List<UserSearchResponse> userList;
}
