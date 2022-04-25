package com.walkhub.walkhub.domain.exercise.domain;

import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@Entity
public class Exercise extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ColumnDefault("0")
    private Integer walkCount;

    private ZonedDateTime endAt;

    @NotNull
    @ColumnDefault("0")
    private Integer distance;

    @NotNull
    @ColumnDefault("0")
    private Double calorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ColumnDefault("6000")
    private Integer goal;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private GoalType goalType;

    @NotNull
    @ColumnDefault("0")
    private Boolean isExercising;

    @NotNull
    @ColumnDefault("0")
    private Long cheeringCount;

    @NotNull
    @ColumnDefault(DefaultImage.CHALLENGE_IMAGE)
    private String imageUrl;

    @NotNull
    @ColumnDefault("0")
    private Integer pausedTime;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.REMOVE)
    private List<Location> locations;

    @Builder
    public Exercise(User user, Integer goal, GoalType goalType) {
        this.user = user;
        this.goalType = goalType;
        if (goal != null) this.goal = goal;
        this.isExercising = true;
    }

    public void closeExercise(Integer walkCount, Integer distance, Double calorie, String imageUrl,
                              Integer pausedTime) {
        this.walkCount = walkCount;
        this.distance = distance;
        this.calorie = calorie;
        this.endAt = ZonedDateTime.now();
        if (imageUrl != null) this.imageUrl = imageUrl;
        this.isExercising = false;
        this.pausedTime = pausedTime;
    }

    public void addCheeringCount() {
        this.cheeringCount++;
    }

}
