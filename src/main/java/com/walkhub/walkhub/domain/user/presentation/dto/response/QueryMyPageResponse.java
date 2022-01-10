package com.walkhub.walkhub.domain.user.presentation.dto.response;

import com.walkhub.walkhub.domain.user.domain.type.Sex;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryMyPageResponse {

	private final Long id;
	private final String name;
	private final String profileImage;
	private final String schoolName;
	private final Integer grade;
	private final Integer classNum;
	private final BigDecimal height;
	private final Integer weight;
	private final String birthday;
	private final Sex sex;
	private final TitleBadge titleBadge;

	@Getter
	@Builder
	public static class TitleBadge {
		private final Long id;
		private final String name;
		private final String image;
	}
}
