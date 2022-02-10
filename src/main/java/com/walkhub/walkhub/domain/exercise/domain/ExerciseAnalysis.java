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
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ExerciseAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer calorie;

    @NotNull
    private Integer walkCount;

    @NotNull
    private Integer distance;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long levelId;

    @NotNull
    private Integer foodCalorie;

    @NotNull
    private LocalDateTime walkTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Builder
    public ExerciseAnalysis(Integer calorie, Integer walkCount,
                            Integer distance, LocalDate date, User user, Long levelId,
                            Integer foodCalorie, LocalDateTime walkTime) {
        this.calorie = calorie;
        this.walkCount = walkCount;
        this.distance = distance;
        this.date = date;
        this.user = user;
        this.levelId = levelId;
        this.foodCalorie = foodCalorie;
        this.walkTime = walkTime;
    }

    public void updateExerciseAnalysis(SaveExerciseAnalysisRequest request) {
        this.calorie = request.getCalorie();
        this.date = request.getDate();
        this.distance = request.getDistance();
        this.walkCount = request.getWalkCount();
    }

}
