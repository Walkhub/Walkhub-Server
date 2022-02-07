package com.walkhub.walkhub.domain.challenge.domain;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.Scope;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
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
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate endAt;

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
                     LocalDate createdAt, LocalDate endAt, Scope scope, User user) {
        this.name = name;
        this.content = content;
        this.goal = goal;
        this.award = award;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.scope = scope;
        this.user = user;

    }
}
