package com.walkhub.walkhub.domain.exercise.domain;

import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

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
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Entity
public class Exercise extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer walkCount;

    private LocalDateTime endAt;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer distance;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Double calorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, columnDefinition = "integer default 6000")
    private Integer goal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GoalType goalType;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isExercising;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Long cheeringCount;

    @Column(nullable = false, columnDefinition = "varchar(255) default " + DefaultImage.EXERCISE_IMAGE)
    private String imageUrl;

    @Builder
    public Exercise(User user, Integer goal, GoalType goalType) {
        this.user = user;
        this.goalType = goalType;
        this.goal = goal;
        this.isExercising = true;
    }

    public void closeExercise(Integer walkCount, Integer distance, Double calorie) {
        this.walkCount = walkCount;
        this.distance = distance;
        this.calorie = calorie;
        this.endAt = LocalDateTime.now();
        this.isExercising = false;
    }

    public void addCheeringCount() {
        this.cheeringCount++;
    }

}
