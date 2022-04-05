package com.walkhub.walkhub.domain.badge.domain;

import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Cacheable
public class Badge extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    private String name;

    @ColumnDefault(DefaultImage.BADGE_IMAGE)
    private String imageUrl;

    @NotNull
    private String unlockCondition;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BadgeType code;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.REMOVE)
    private List<BadgeCollection> badgeCollections;

    @Builder
    public Badge(String name, String imageUrl, String unlockCondition, BadgeType code) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.unlockCondition = unlockCondition;
        this.code = code;
    }
}
