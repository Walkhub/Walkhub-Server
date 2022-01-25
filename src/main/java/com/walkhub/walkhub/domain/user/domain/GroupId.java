package com.walkhub.walkhub.domain.user.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GroupId implements Serializable {

    private Integer grade;

    private Integer classNum;

    private String school;
}
