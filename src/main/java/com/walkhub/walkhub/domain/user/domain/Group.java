package com.walkhub.walkhub.domain.user.domain;

import com.walkhub.walkhub.domain.school.domain.School;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(GroupId.class)
@Entity
public class Group {

    @Id
    private Integer grade;

    @Id
    private Integer classNum;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_code")
    private School school;

    @Column(length = 7, nullable = false)
    private String classCode;

    @Builder
    public Group(Integer grade, Integer classNum, School school, String classCode) {
        this.grade = grade;
        this.classNum = classNum;
        this.school = school;
        this.classCode = classCode;
    }
}
