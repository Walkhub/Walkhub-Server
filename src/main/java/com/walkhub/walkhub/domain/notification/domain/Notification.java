package com.walkhub.walkhub.domain.notification.domain;

import com.walkhub.walkhub.domain.notification.domain.type.Type;
import com.walkhub.walkhub.global.enums.Scope;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification {

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

}
