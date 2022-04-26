package com.walkhub.walkhub.domain.badge.domain;

import com.walkhub.walkhub.domain.badge.enums.BadgeType;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
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
    @Column(unique = true)
    private String name;

    @NotNull
    @ColumnDefault(DefaultImage.BADGE_IMAGE)
    private String imageUrl;

    @NotNull
    @Column(unique = true)
    private String unlockCondition;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private BadgeType code;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.REMOVE)
    private List<BadgeCollection> badgeCollections;
}
