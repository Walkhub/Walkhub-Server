package com.walkhub.walkhub.domain.notification.domain;

import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String data;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private UserScope userScope;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic type;

    @Builder
    public NotificationEntity(String title, String content, String data, UserScope userScope, Topic type) {
        this.title = title;
        this.content = content;
        this.data = data;
        this.userScope = userScope;
        this.type = type;
    }

}
