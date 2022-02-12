package com.walkhub.walkhub.domain.user.domain;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.type.HealthInfo;
import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

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

    @ColumnDefault(DefaultImage.USER_PROFILE_IMAGE)
    private String profileImageUrl;

    @NotNull
    @Length(max = 6)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(columnDefinition = "TINYINT")
    private Integer number;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Boolean isMeasuring;

    @Embedded
    @Setter
    private HealthInfo healthInfo;

    @NotNull
    @Length(max = 6)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_badge_id")
    private Badge badge;

    @Column(name = "app_device_token")
    private String deviceToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "max_level_id")
    private CalorieLevel maxLevel;

    @NotNull
    @ColumnDefault("10000")
    private Integer dailyWalkCountGoal;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Exercise> exerciseList;
    
    @Builder
    public User(Long id, String accountId, String password, String phoneNumber, String name,
                Authority authority, Section section, School school, boolean isMeasuring,
                Integer weight, BigDecimal height, Sex sex, Badge badge, String deviceToken, Integer dailyWalkCountGoal) {
        this.id = id;
        this.accountId = accountId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.authority = authority;
        this.section = section;
        this.school = school;
        this.isMeasuring = isMeasuring;
        this.healthInfo = new HealthInfo(weight, height);
        this.sex = sex;
        this.badge = badge;
        this.deviceToken = deviceToken;
        this.dailyWalkCountGoal = dailyWalkCountGoal;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public void updateUser(UpdateUserInfoRequest request) {
        this.name = request.getName();
        this.profileImageUrl = request.getProfileImageUrl();
        this.sex = request.getSex();
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void updatedailyWalkCountGoal(Integer dailyWalkCountGoal) {
        this.dailyWalkCountGoal = dailyWalkCountGoal;
    }

}
