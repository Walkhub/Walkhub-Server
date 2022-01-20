package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.GroupId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, GroupId> {
}
