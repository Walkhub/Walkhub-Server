package com.walkhub.walkhub.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CalorieLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 10)
    private String foodName;

    @NotNull
    private Integer size;

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
    public CalorieLevel(String foodName, Integer size, String message, String foodImageUrl, Integer level, Double calorie) {
        this.foodName = foodName;
        this.size = size;
        this.message = message;
        this.foodImageUrl = foodImageUrl;
        this.level = level;
        this.calorie = calorie;
    }
}
