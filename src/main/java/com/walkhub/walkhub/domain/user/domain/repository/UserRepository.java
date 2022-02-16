package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.Authority;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByAccountId(String accountId);
    Optional<User> findByPhoneNumber(String phoneNumber);
    User findBySectionAndAuthority(Section section, Authority authority);
    List<User> findAllBySectionAndAuthority(Section section, Authority authority);
    List<User> findAllBySchoolIdAndNameContaining(Long id, String name);

    @Transactional
    @Modifying
    @Query("update User u set u.section = null where u.section = :section")
    void setUserSectionNull(@Param("section") Section section);
}
