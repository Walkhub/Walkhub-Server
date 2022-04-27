package com.walkhub.walkhub.domain.calorielevel.domain;

import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Cacheable
public class CalorieLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 10)
    private String foodName;

    @NotNull
    private String size;

    @NotNull
    private String message;

    @NotNull
    @ColumnDefault(DefaultImage.CALORIE_LEVEL_IMAGE)
    private String foodImageUrl;

    @NotNull
    @Column(columnDefinition = "TINYINT", unique = true)
    private Integer level;

    @NotNull
    private Double calorie;

}
