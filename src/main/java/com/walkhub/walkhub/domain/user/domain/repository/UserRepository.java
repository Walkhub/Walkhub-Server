package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.Authority;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByAccountId(String accountId);
    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findAllBySchoolIdAndNameContaining(Long id, String name);
    @Query("select u from User u left join fetch u.section where u.school = :school and u.authority = :authority")
    List<User> findAllBySchoolAndAuthority(@Param("school") School school, @Param("authority") Authority authority);

    @Transactional
    @Modifying
    @Query("update User u set u.section = null where u.section = :section")
    void setUserSectionNull(@Param("section") Section section);
}
