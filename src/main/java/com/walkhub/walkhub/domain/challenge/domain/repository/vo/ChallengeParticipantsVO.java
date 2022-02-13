package com.walkhub.walkhub.domain.challenge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ChallengeParticipantsVO {
    private final Long userId;
    private final Integer grade;
    private final Integer classNum;
    private final Integer number;
    private final String name;
    private final String profileImageUrl;
    private final String schoolName;
    private final Boolean isSuccess;
    private final List<LocalDate> exerciseAnalysesDates;

    @QueryProjection
    public ChallengeParticipantsVO(Long userId, Integer grade, Integer classNum, Integer number,
                                   String name, String profileImageUrl, String schoolName, Boolean isSuccess, List<LocalDate> exerciseAnalysesDates) {
        this.userId = userId;
        this.grade = grade;
        this.classNum = classNum;
        this.number = number;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.schoolName = schoolName;
        this.isSuccess = isSuccess;
        this.exerciseAnalysesDates = exerciseAnalysesDates;
    }
}
