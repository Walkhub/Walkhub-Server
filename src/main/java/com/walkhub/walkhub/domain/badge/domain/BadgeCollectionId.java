package com.walkhub.walkhub.domain.badge.domain;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class BadgeCollectionId implements Serializable {

	private Long badge;

	private Long user;
}
