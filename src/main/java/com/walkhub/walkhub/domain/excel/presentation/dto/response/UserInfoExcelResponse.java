package com.walkhub.walkhub.domain.excel.presentation.dto.response;

import com.walkhub.walkhub.global.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserInfoExcelResponse {

    private final List<UserInfoResponse> userList;

    @Getter
    @Builder
    public static class UserInfoResponse {
        private final String name;
        private final Integer grade;
        private final Integer classNum;
        private final Integer number;
        private final Integer allWalkCount;
        private final Integer averageWalkCount;
        private final Integer allDistance;
        private final Integer averageDistance;
        private final Authority authority;
        private final String schoolName;
    }

}
