package com.walkhub.walkhub.domain.notification.domain;

import com.walkhub.walkhub.domain.notification.domain.type.Type;
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
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 3, nullable = false)
    private Type type;

    @Column(nullable = false)
    private Long value;

    @Enumerated(EnumType.STRING)
    @Column(length = 3, nullable = false)
    private Scope scope;

    private LocalDateTime createAt;

    @Builder
    public NotificationEntity(String title, String content, Type type, Long value, Scope scope, LocalDateTime createAt) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.value = value;
        this.scope = scope;
        this.createAt = createAt;
    }

}
