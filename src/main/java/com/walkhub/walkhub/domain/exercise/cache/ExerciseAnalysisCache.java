package com.walkhub.walkhub.domain.exercise.cache;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseAnalysisCache implements Serializable {

    private String name;

    private Integer classNum;

    private Integer grade;

    private Integer number;

    private Integer walkCount;

    private String profileImageUrl;

    @TimeToLive
    private LocalDateTime timeToLive;

    @Builder
    private ExerciseAnalysisCache(String name, Integer classNum, Integer grade, Integer number,
                                  Integer walkCount, String profileImageUrl) {
        this.name = name;
        this.classNum = classNum;
        this.grade = grade;
        this.number = number;
        this.walkCount = walkCount;
        this.profileImageUrl = profileImageUrl;
        this.timeToLive = LocalDateTime.now().plusDays(1);
    }
}