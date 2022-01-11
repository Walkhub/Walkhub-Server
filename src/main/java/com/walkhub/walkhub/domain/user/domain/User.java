package com.walkhub.walkhub.domain.user.domain;

import com.walkhub.walkhub.domain.user.domain.type.HealthInfo;
import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;

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

	private String profileImageUrl;

	@Column(columnDefinition = "char(4)", nullable = false)
	@Enumerated(EnumType.STRING)
	private Authority authority;

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
	private Boolean isMeasuring;

	@Embedded
	private HealthInfo healthInfo;

	@Column(columnDefinition = "char(1)")
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@Column(columnDefinition = "char(8)")
	private String birthday;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title_badge_id",  nullable = false)
	private Badge badge;

	private String deviceToken;

	@Builder
	public User(Long id, String accountId, String password, String phoneNumber, String name,
				String profileImageUrl, Authority authority, Group group, School school, boolean isMeasuring,
				Integer weight, BigDecimal height, Sex sex, String birthday, Badge badge,
				String deviceToken) {
		this.id = id;
		this.accountId = accountId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.profileImageUrl = profileImageUrl;
		this.authority = authority;
		this.group = group;
		this.school = school;
		this.isMeasuring = isMeasuring;
		this.healthInfo = new HealthInfo(weight, height);
		this.sex = sex;
		this.birthday = birthday;
		this.badge = badge;
		this.deviceToken = deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public void updateUser(UpdateUserInfoRequest request) {
		this.name = request.getName();
		this.profileImageUrl = request.getProfileImageUrl();
		this.birthday = request.getBirthday();
		this.sex = request.getSex();
	}

}
