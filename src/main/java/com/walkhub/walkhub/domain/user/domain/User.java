package com.walkhub.walkhub.domain.user.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String accountId;

	@Column(length = 60, nullable = false)
	private String password;

	@Column(length = 11, nullable = false)
	private String phoneNumber;

	@Column(length = 10, nullable = false)
	private String name;

	private String profileImage;

	@Column(columnDefinition = "char(4)", nullable = false)
	private String authority;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "grade"),
		@JoinColumn(name = "class"),
		@JoinColumn(name = "agency_code")
	})
	private Group group;

	@ManyToOne(fetch = FetchType.LAZY)
	private School school;

	@ColumnDefault("0")
	@Column(nullable = false)
	private boolean isMeasuring;

	@Column(columnDefinition = "TINYINT unsigned")
	private Integer weight;

	@Digits(integer = 3, fraction = 1)
	private BigDecimal height;

	@Column(columnDefinition = "char(1)")
	private String sex;

	@Column(columnDefinition = "char(8)")
	private String birthday;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title_badge_id",  nullable = false)
	private Badge badge;

	private String webDeviceToken;

	private String appDeviceToken;

	@Builder
	public User(Long id, String accountId, String password, String phoneNumber, String name,
		String profileImage, String authority, Group group, School school, boolean isMeasuring,
		Integer weight, BigDecimal height, String sex, String birthday, Badge badge,
		String webDeviceToken, String appDeviceToken) {
		this.id = id;
		this.accountId = accountId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.profileImage = profileImage;
		this.authority = authority;
		this.group = group;
		this.school = school;
		this.isMeasuring = isMeasuring;
		this.weight = weight;
		this.height = height;
		this.sex = sex;
		this.birthday = birthday;
		this.badge = badge;
		this.webDeviceToken = webDeviceToken;
		this.appDeviceToken = appDeviceToken;
	}

}
