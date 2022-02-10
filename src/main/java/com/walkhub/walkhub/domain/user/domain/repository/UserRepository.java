package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.Authority;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByAccountId(String accountId);
    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findAllBySectionAndAuthority(Section section, Authority authority);
}
