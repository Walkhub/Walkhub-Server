package com.walkhub.walkhub.domain.exercise.domain;

import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private LocalDateTime endAt;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer distance;

    @Column(nullable = false)
    private Integer calorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ColumnDefault("6000")
    @Column(nullable = false)
    private Integer goal;

    @Column(columnDefinition = "char(1)", nullable = false)
    @Enumerated(EnumType.STRING)
    private GoalType goalType;

    @Builder
    public Exercise(User user, Integer goal, GoalType goalType) {
        this.user = user;
        this.goalType = goalType;
        if(goal != null) this.goal = goal;
    }

    public void closeExercise(Integer walkCount, Integer distance, Integer calorie) {
        this.walkCount = walkCount;
        this.distance = distance;
        this.calorie = calorie;
        this.endAt = LocalDateTime.now();
    }

}
