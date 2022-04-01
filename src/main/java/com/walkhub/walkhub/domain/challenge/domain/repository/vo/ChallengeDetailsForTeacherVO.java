package com.walkhub.walkhub.domain.challenge.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ChallengeDetailsForTeacherVO {
    private final Long userId;
    private final String userName;
    private final Integer grade;
    private final Integer classNum;
    private final Integer number;
    private final String schoolName;
    private final String profileImageUrl;
    private final Integer totalValue;
    private final Long progress;
    private final Boolean isSuccess;
    private final LocalDate successDate;

    @QueryProjection
    public ChallengeDetailsForTeacherVO(Long userId, String userName, Integer grade,
                                        Integer classNum, Integer number, String schoolName,
                                        String profileImageUrl, Integer totalValue, Long progress,
                                        Boolean isSuccess, LocalDate successDate) {
        this.userId = userId;
        this.userName = userName;
        this.grade = grade;
        this.classNum = classNum;
        this.number = number;
        this.schoolName = schoolName;
        this.profileImageUrl = profileImageUrl;
        this.totalValue = totalValue;
        this.progress = progress;
        this.isSuccess = isSuccess;
        this.successDate = successDate;
    }

}
