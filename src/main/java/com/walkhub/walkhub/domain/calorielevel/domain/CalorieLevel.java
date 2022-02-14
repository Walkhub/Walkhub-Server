package com.walkhub.walkhub.domain.calorielevel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CalorieLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Length(max = 10)
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
	private Integer calorie;

	@Builder
	public CalorieLevel(String foodName, Integer size, String message, String foodImageUrl, Integer level,
		Integer calorie) {
		this.foodName = foodName;
		this.size = size;
		this.message = message;
		this.foodImageUrl = foodImageUrl;
		this.level = level;
		this.calorie = calorie;
	}
}
