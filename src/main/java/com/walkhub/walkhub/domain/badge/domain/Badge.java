package com.walkhub.walkhub.domain.badge.domain;

import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Badge extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 20)
    private String name;

    @ColumnDefault(DefaultImage.BADGE_IMAGE)
    private String imageUrl;

    @NotNull
    private String condition;

    @Builder
    public Badge(String name, String imageUrl,
                 String condition) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.condition = condition;
    }
}
