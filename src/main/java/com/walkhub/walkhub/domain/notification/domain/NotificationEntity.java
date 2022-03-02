package com.walkhub.walkhub.domain.notification.domain;

import com.walkhub.walkhub.domain.notification.domain.type.Type;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.global.enums.UserScope;
import javax.validation.constraints.NotNull;
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
import org.hibernate.validator.constraints.Length;

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

    @NotNull
    @Length(max = 9)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private String data;

    @NotNull
    @Length(max = 6)
    @Enumerated(EnumType.STRING)
    private UserScope userScope;

    @Builder
    public NotificationEntity(String title, String content, Type type, String data, UserScope userScope) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.data = data;
        this.userScope = userScope;
    }

}
