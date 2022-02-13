package com.walkhub.walkhub.domain.exercise.domain;

import com.walkhub.walkhub.domain.exercise.presentation.dto.request.SaveExerciseAnalysisRequest;
import com.walkhub.walkhub.domain.user.domain.User;
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
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ExerciseAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer calorie;

    @Column(nullable = false)
    private Integer walkCount;

    @Column(nullable = false)
    private Integer distance;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double exerciseTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public ExerciseAnalysis(Integer calorie, Integer walkCount, Integer distance,
                            LocalDate date, Double exerciseTime, User user) {
        this.calorie = calorie;
        this.walkCount = walkCount;
        this.distance = distance;
        this.date = date;
        this.exerciseTime = exerciseTime;
        this.user = user;
    }

    public void updateExerciseAnalysis(SaveExerciseAnalysisRequest request) {
        this.calorie = request.getCalorie();
        this.date = request.getDate();
        this.distance = request.getDistance();
        this.walkCount = request.getWalkCount();
    }

}
