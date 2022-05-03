package com.walkhub.walkhub.domain.excel.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserInfoExcelResponse {

    private final List<UserInfoVo> userList;

    @Getter
    public static class UserInfoVo {

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

        @QueryProjection
        public UserInfoVo(String name, Integer grade, Integer classNum, Integer number,
                          Integer allWalkCount, Integer averageWalkCount, Integer allDistance,
                          Integer averageDistance, Authority authority, String schoolName) {
            this.name = name;
            this.grade = grade;
            this.classNum = classNum;
            this.number = number;
            this.allWalkCount = allWalkCount;
            this.averageWalkCount = averageWalkCount;
            this.allDistance = allDistance;
            this.averageDistance = averageDistance;
            this.authority = authority;
            this.schoolName = schoolName;
        }
    }
}
