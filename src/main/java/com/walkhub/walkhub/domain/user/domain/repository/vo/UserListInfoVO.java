package com.walkhub.walkhub.domain.user.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserListInfoVO {
    private final Long userId;
    private final String name;
    private final String profileImageUrl;
    private final Integer grade;
    private final Integer classNum;
    private final Integer number;
    private final Double averageWalkCount;
    private final Integer totalWalkCount;
    private final Double averageDistance;
    private final Integer totalDistance;
    private final Boolean isTeacher;

    @QueryProjection
    public UserListInfoVO(Long userId, String name, String profileImageUrl, Integer grade, Integer classNum,
                          Integer number, Double averageWalkCount, Integer totalWalkCount, Double averageDistance,
                          Integer totalDistance, Boolean isTeacher) {
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
        this.isTeacher = isTeacher;
    }
}
