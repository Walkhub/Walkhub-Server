package com.walkhub.walkhub.domain.user.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserDetailsVO {
    private final Long userId;
    private final String name;
    private final String profileImageUrl;
    private final Integer grade;
    private final Integer classNum;
    private final Integer number;
    private final Integer averageWalkCount;
    private final Integer totalWalkCount;
    private final Integer averageDistance;
    private final Integer totalDistance;

    @QueryProjection
    public UserDetailsVO(Long userId, String name, String profileImageUrl,
                         Integer grade, Integer classNum, Integer number,
                         Integer averageWalkCount, Integer totalWalkCount,
                         Integer averageDistance, Integer totalDistance) {
        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.grade = grade;
        this.classNum = classNum;
        this.number = number;
        this.averageWalkCount = averageWalkCount;
        this.totalWalkCount = totalWalkCount;
        this.averageDistance = averageDistance;
        this.totalDistance = totalDistance;
    }
}
