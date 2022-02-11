package com.walkhub.walkhub.domain.exercise.domain;

import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Exercise extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer walkCount;

    private LocalDateTime endAt;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer distance;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer calorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ColumnDefault("6000")
    @Column(nullable = false)
    private Integer goal;

    @NotNull
    @Length(max = 8)
    @Enumerated(EnumType.STRING)
    private GoalType goalType;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Boolean isExercising;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Long cheeringCount;

    @NotNull
    @ColumnDefault(DefaultImage.EXERCISE_IMAGE)
    private String imageUrl;

    @Builder
    public Exercise(User user, Integer goal, GoalType goalType) {
        this.user = user;
        this.goalType = goalType;
        this.goal = goal;
    }

    public void closeExercise(Integer walkCount, Integer distance, Integer calorie) {
        this.walkCount = walkCount;
        this.distance = distance;
        this.calorie = calorie;
        this.endAt = LocalDateTime.now();
    }

    public void addCheeringCount() {
        this.cheeringCount++;
    }

}
