package com.walkhub.walkhub.domain.challenge.domain;

import com.walkhub.walkhub.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(columnDefinition = "BIT(1)")
    private Boolean isSuccess;

    @Builder
    public ChallengeStatus(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
