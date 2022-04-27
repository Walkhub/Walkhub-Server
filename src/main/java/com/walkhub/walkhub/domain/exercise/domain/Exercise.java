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

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer walkCount;

    private ZonedDateTime endAt;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer distance;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Double calorie;

    @ColumnDefault("6000")
    @Column(nullable = false)
    private Integer goal;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private GoalType goalType;

    @ColumnDefault("1")
    @Column(nullable = false)
    private Boolean isExercising;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Long cheeringCount;

    @ColumnDefault(DefaultImage.CHALLENGE_IMAGE)
    private String imageUrl;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer pausedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.REMOVE)
    private List<Location> locations;

    @Builder
    public Exercise(User user, Integer goal, GoalType goalType) {
        this.user = user;
        this.goalType = goalType;
        this.goal = goal;

        user.updateIsMeasuring(true);
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

        user.updateIsMeasuring(false);
    }

    public void addCheeringCount() {
        this.cheeringCount++;
    }

}
