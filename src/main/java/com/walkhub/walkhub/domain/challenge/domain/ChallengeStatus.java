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
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "BIT(1)", nullable = false)
    private Boolean isSuccess;

    @Builder
    public ChallengeStatus(Challenge challenge, User user) {
        this.challenge = challenge;
        this.user = user;
        this.isSuccess = false;
    }

}
