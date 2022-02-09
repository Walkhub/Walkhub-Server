package com.walkhub.walkhub.domain.challenge.domain;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.UserScope;
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
import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @ColumnDefault(DefaultImage.CHALLENGE_IMAGE)
    private String imageUrl;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate startAt;

    @Column(nullable = false)
    private LocalDate endAt;

    @Column(length = 200, nullable = false)
    private String award;

    @NotNull
    @Length(max = 6)
    @Enumerated(EnumType.STRING)
    private UserScope userScope;

    @NotNull
    @Length(max = 3)
    @Enumerated(EnumType.STRING)
    private GoalScope goalScope;

    @NotNull
    @Length(max = 8)
    @Enumerated(EnumType.STRING)
    private GoalType goalType;

    @Column(nullable = false)
    private Long goal;

    @ColumnDefault("1")
    @NotNull
    private Long successStandard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Challenge(String name, String content, Long goal, String award, String imageUrl,
                     LocalDate startAt, LocalDate endAt, UserScope userScope, GoalType goalType,
                     GoalScope goalScope, Long successStandard, User user) {
        this.name = name;
        this.content = content;
        this.goal = goal;
        this.goalType = goalType;
        this.goalScope = goalScope;
        this.successStandard = successStandard;
        this.award = award;
        this.imageUrl = imageUrl;
        this.startAt = startAt;
        this.endAt = endAt;
        this.userScope = userScope;
        this.user = user;

    }
}
