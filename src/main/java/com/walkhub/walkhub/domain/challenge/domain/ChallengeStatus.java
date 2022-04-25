package com.walkhub.walkhub.domain.challenge.domain;

import com.walkhub.walkhub.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ChallengeStatusId.class)
@Entity
public class ChallengeStatus {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private LocalDate createdAt;

    @Builder
    public ChallengeStatus(Challenge challenge, User user) {
        this.challenge = challenge;
        this.user = user;
        this.createdAt = LocalDate.now();
    }

}
