package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.user.domain.UserAuthCode;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthCodeRepository extends CrudRepository<UserAuthCode, String> {
}
