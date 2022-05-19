package com.walkhub.walkhub.domain.notification.domain;


import com.walkhub.walkhub.domain.notification.domain.type.NotificationType;
import com.walkhub.walkhub.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Length(max = 20)
    private NotificationType type;

    @ColumnDefault("0")
    private Boolean isSubscribe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Topic(NotificationType type, Boolean isSubscribe, User user) {
        this.type = type;
        this.isSubscribe = isSubscribe;
        this.user = user;
    }
}
