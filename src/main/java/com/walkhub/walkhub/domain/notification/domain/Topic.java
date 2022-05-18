package com.walkhub.walkhub.domain.notification.domain;


import com.walkhub.walkhub.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 9, nullable = false)
    private String title;

    @ColumnDefault("0")
    private Boolean isSubscribe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Topic(String title, Boolean isSubscribe, User user) {
        this.title = title;
        this.isSubscribe = isSubscribe;
        this.user = user;
    }
}
