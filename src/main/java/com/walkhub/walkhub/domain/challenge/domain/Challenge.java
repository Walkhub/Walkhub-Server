package com.walkhub.walkhub.domain.challenge.domain;

import com.walkhub.walkhub.domain.challenge.domain.type.GoalScope;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.UpdateChallengeRequest;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.UserScope;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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

    @Column(nullable = false)
    private String award;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private UserScope userScope;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private GoalScope goalScope;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private GoalType goalType;

    @NotNull
    private Integer goal;

    @NotNull
    @ColumnDefault("1")
    private Integer successStandard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.REMOVE)
    private List<ChallengeStatus> challengeStatuses;

    @Builder
    public Challenge(String name, String imageUrl, String content, LocalDate startAt,
                     LocalDate endAt, String award, UserScope userScope, GoalScope goalScope,
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

    public void updateChallenge(UpdateChallengeRequest request) {
        this.name = request.getName();
        this.content = request.getContent();
        this.imageUrl = request.getImageUrl() == null ? DefaultImage.CHALLENGE_IMAGE : request.getImageUrl();
        this.startAt = request.getStartAt();
        this.endAt = request.getEndAt();
        this.award = request.getAward();
        this.goal = request.getGoal();
        this.goalType = request.getGoalType();
        this.goalScope = request.getGoalScope();
        this.successStandard = request.getSuccessStandard();
    }

    public boolean isWriter(Long userId) {
        return user.getId().equals(userId);
    }
}
