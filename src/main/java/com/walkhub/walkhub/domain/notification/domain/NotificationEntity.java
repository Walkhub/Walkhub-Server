package com.walkhub.walkhub.domain.notification.domain;

import com.walkhub.walkhub.domain.notification.domain.type.Type;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.global.enums.Scope;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "char(9)", nullable = false)
    private Type type;

    @Column(nullable = false)
    private Long value;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "char(6)", nullable = false)
    private Scope scope;

    @Builder
    public NotificationEntity(String title, String content, Type type, Long value, Scope scope) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.value = value;
        this.scope = scope;
    }

}
