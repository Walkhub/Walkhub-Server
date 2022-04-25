package com.walkhub.walkhub.domain.exercise.domain;

import com.walkhub.walkhub.domain.exercise.presentation.dto.request.SaveExerciseAnalysisRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ExerciseAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double calorie;

    @NotNull
    private Integer walkCount;

    @NotNull
    private Integer distance;

    @NotNull
    private LocalDate date;

    @NotNull
    private Double exerciseTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public ExerciseAnalysis(Double calorie, Integer walkCount, Double exerciseTime,
                            Integer distance, LocalDate date, User user) {
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
