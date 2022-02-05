package com.walkhub.walkhub.domain.user.domain;

import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Badge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = false)
	private String name;

	@ColumnDefault(DefaultImage.BADGE_IMAGE)
	private String imageUrl;

	@Builder
	public Badge(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
	}
}
