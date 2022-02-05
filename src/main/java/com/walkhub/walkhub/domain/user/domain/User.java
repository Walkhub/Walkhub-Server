package com.walkhub.walkhub.domain.user.domain;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.type.HealthInfo;
import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    @Setter
    private HealthInfo healthInfo;

    @Column(columnDefinition = "char(1)")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    private LocalDate birthday;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_badge_id")
    private Badge badge;

    private String deviceToken;

    @Builder
    public User(Long id, String accountId, String password, String phoneNumber, String name,
                String profileImageUrl, Authority authority, Group group, School school,
                Integer weight, BigDecimal height, Sex sex, LocalDate birthday, Badge badge,
                boolean isMeasuring, String deviceToken) {
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

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public School getRealSchool() {
        return this.group.getSchool();
    }

    public String getRealSchoolAgencyCode() {
        return this.getRealSchool().getAgencyCode();
    }

    public String getRealSchoolName() {
        return this.getRealSchool().getName();
    }

    public String getClassCode() {
        return this.group.getClassCode();
    }

}
