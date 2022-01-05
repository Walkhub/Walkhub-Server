package com.walkhub.walkhub.domain.exercise.domain;

import com.walkhub.walkhub.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public ExerciseAnalysis(Integer calorie, Integer walkCount,
                            Integer distance, LocalDateTime date) {

        this.calorie = calorie;
        this.walkCount = walkCount;
        this.distance = distance;
        this.date = date;
    }

}
