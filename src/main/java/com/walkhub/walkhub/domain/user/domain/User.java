package com.walkhub.walkhub.domain.user.domain;

import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.type.HealthInfo;
import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.domain.user.exception.SectionNotFoundException;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Cacheable
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "user_uk",
                columnNames = {"section_id", "number"}
        )
})
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(unique = true)
    private String accountId;

    @NotNull
    @Size(max = 60)
    private String password;

    @NotNull
    @Size(max = 11)
    @Column(unique = true)
    private String phoneNumber;

    @NotNull
    @Size(max = 10)
    private String name;

    @NotNull
    @ColumnDefault(DefaultImage.USER_PROFILE_IMAGE)
    private String profileImageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 7)
    private Authority authority;

    @Column(columnDefinition = "TINYINT unsigned")
    private Integer number;

    @NotNull
    @ColumnDefault("0")
    private Boolean isMeasuring;

    @Setter
    @Embedded
    private HealthInfo healthInfo;

    @Setter
    @NotNull
    @ColumnDefault("'X'")
    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private Sex sex;

    @NotNull
    @ColumnDefault("10000")
    private Integer dailyWalkCountGoal;

    @Column(name = "app_device_token")
    private String deviceToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_badge_id", nullable = false)
    private Badge badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "max_level_id", nullable = false)
    private CalorieLevel maxLevel;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ChallengeStatus> challengeStatuses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ExerciseAnalysis> exerciseAnalyses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Exercise> exerciseList;

    @Builder
    public User(String accountId, String password, String phoneNumber, String name,
                Authority authority, Section section, School school, boolean isMeasuring,
                Integer weight, BigDecimal height, Sex sex, Badge badge, CalorieLevel calorieLevel, String deviceToken) {
        this.accountId = accountId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.authority = authority;
        this.section = section;
        this.school = school;
        this.isMeasuring = isMeasuring;
        this.healthInfo = new HealthInfo(weight, height);
        this.dailyWalkCountGoal = 10000;
        if (sex != null) this.sex = sex;
        this.badge = badge;
        this.maxLevel = calorieLevel;
        this.deviceToken = deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public void updateUser(UpdateUserInfoRequest request) {
        this.name = request.getName();
        this.profileImageUrl = request.getProfileImageUrl();
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
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

    public void setAuthorityTeacher() {
        this.authority = Authority.TEACHER;
    }

    public void updateDailyWalkCountGoal(Integer dailyWalkCountGoal) {
        this.dailyWalkCountGoal = dailyWalkCountGoal;
    }

    public Section getSection() {
        if (!hasSection()) {
            throw SectionNotFoundException.EXCEPTION;
        }
        return this.section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public boolean hasSection() {
        return this.section != null;
    }

    public void setMaxLevel(CalorieLevel calorieLevel) {
        this.maxLevel = calorieLevel;
    }

    public void setSectionNull() {
        this.section = null;
    }

    public void updateRootUserPassword(String password) {
        if (this.authority != Authority.ROOT) {
            throw InvalidRoleException.EXCEPTION;
        }

        this.password = password;
    }

    public void updateIsMeasuring(Boolean isMeasuring) {
        this.isMeasuring = isMeasuring;
    }

    public BigDecimal getHeight() {
        return this.healthInfo.getHeight();
    }

    public Integer getWeight() {
        return this.healthInfo.getWeight();
    }

    public String getSchoolName() {
        return this.school.getName();
    }

}
