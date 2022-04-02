package com.walkhub.walkhub.domain.user.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;

@Getter
public class UserDetailsVO {
    private final List<Integer> walkCountList;
    private final Integer averageWalkCount;
    private final Integer totalWalkCount;
    private final Integer averageDistance;
    private final Integer totalDistance;

    @QueryProjection
    public UserDetailsVO(List<Integer> walkCountList, Integer averageWalkCount, Integer totalWalkCount,
                         Integer averageDistance, Integer totalDistance) {
        this.walkCountList = walkCountList;
        this.averageWalkCount = averageWalkCount;
        this.totalWalkCount = totalWalkCount;
        this.averageDistance = averageDistance;
        this.totalDistance = totalDistance;
    }
}
