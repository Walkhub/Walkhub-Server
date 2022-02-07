package com.walkhub.walkhub.domain.user.domain;

import com.walkhub.walkhub.domain.school.domain.School;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 7, nullable = false)
    private String classCode;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer grade;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer classNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @Builder
    public Group(Integer grade, Integer classNum, School school, String classCode) {
        this.grade = grade;
        this.classNum = classNum;
        this.school = school;
        this.classCode = classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
