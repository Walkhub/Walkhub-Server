package com.walkhub.walkhub.domain.challenge.domain;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.UserScope;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

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
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false)
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

    @NotNull
    private Integer goal;

    @NotNull
    @ColumnDefault("1")
    private Integer successStandard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Challenge(String name, String imageUrl, String content, LocalDateTime startAt,
                     LocalDateTime endAt, String award, UserScope userScope, GoalScope goalScope,
                     GoalType goalType, Integer goal, Integer successStandard, User user) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
        this.award = award;
        this.userScope = userScope;
        this.goalScope = goalScope;
        this.goalType = goalType;
        this.goal = goal;
        this.successStandard = successStandard;
        this.user = user;
    }

    public boolean isWriter(Long userId) {
        return user.getId().equals(userId);
    }
}
