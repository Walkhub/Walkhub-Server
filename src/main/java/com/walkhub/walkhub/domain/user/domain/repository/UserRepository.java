package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByAccountId(String accountId);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
