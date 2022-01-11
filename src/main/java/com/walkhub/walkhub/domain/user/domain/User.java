package com.walkhub.walkhub.domain.user.domain;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.Digits;

import com.walkhub.walkhub.domain.user.domain.type.HealthInfo;
import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.*;
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
	@Setter
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
				String profileImage, Authority authority, Group group, School school, boolean isMeasuring,
				Integer weight, BigDecimal height, Sex sex, String birthday, Badge badge,
				String deviceToken) {
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
		this.healthInfo = new HealthInfo(weight, height);
		this.sex = sex;
		this.birthday = birthday;
		this.badge = badge;
		this.deviceToken = deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

}
