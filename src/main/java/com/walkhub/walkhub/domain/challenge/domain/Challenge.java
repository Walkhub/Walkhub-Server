package com.walkhub.walkhub.domain.challenge.domain;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.Scope;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String imageUrl;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(length = 200, nullable = false)
    private String award;

    @Column(columnDefinition = "char(3)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @Column(nullable = false)
    private Long goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Challenge(String name, String content, Long goal, String award,
                     LocalDateTime createAt, LocalDateTime endAt, Scope scope, User user) {
        this.name = name;
        this.content = content;
        this.goal = goal;
        this.award = award;
        this.createAt = createAt;
        this.endAt = endAt;
        this.scope = scope;
        this.user = user;

    }

    public void updateChallenge(String name, String content, Long goal, String award, String imageUrl,
                                LocalDateTime createAt, LocalDateTime endAt, Scope scope) {
        this.name = name;
        this.content = content;
        this.goal = goal;
        this.award = award;
        this.imageUrl = imageUrl;
        this.createAt = createAt;
        this.endAt = endAt;
        this.scope = scope;
    }

    public Challenge updateName(String name) {
        this.name = name;
        return this;
    }

    public Challenge updateContent(String content) {
        this.content = content;
        return this;
    }

    public Challenge updateGoal(Long goal) {
        this.goal = goal;
        return this;
    }

    public Challenge updateAward(String award) {
        this.award = award;
        return this;
    }

    public Challenge updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Challenge updateCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public Challenge UpdateEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
        return this;
    }

    public Challenge updateScope(Scope scope) {
        this.scope = scope;
        return this;
    }
}
