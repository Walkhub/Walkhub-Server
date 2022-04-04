package com.walkhub.walkhub.domain.calorielevel.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Cacheable
public class CalorieLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 10)
    private String foodName;

    @NotNull
    private String size;

    @NotNull
    private String message;

    @NotNull
    private String foodImageUrl;

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private Integer level;

    @NotNull
    private Double calorie;

    @Builder
    public CalorieLevel(String foodName, String size, String message, String foodImageUrl, Integer level,
                        Double calorie) {
        this.foodName = foodName;
        this.size = size;
        this.message = message;
        this.foodImageUrl = foodImageUrl;
        this.level = level;
        this.calorie = calorie;
    }
}
