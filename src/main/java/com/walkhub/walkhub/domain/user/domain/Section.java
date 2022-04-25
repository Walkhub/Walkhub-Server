package com.walkhub.walkhub.domain.user.domain;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Cacheable
@Table(name = "section", uniqueConstraints = {
        @UniqueConstraint(
                name = "section_uk",
                columnNames = {"grade", "classNum", "school_id"}
        )
})
public class Section extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "char(7)", unique = true)
    private String classCode;

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private Integer grade;

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private Integer classNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @OneToMany(mappedBy = "section")
    private List<User> users;

    @Builder
    public Section(Integer grade, Integer classNum, School school, String classCode) {
        this.grade = grade;
        this.classNum = classNum;
        this.school = school;
        this.classCode = classCode;
    }
}
