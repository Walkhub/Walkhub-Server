package com.walkhub.walkhub.domain.teacher.domain.repository;

import com.walkhub.walkhub.domain.teacher.domain.AuthCode;
import org.springframework.data.repository.CrudRepository;

public interface AuthCodeRepository extends CrudRepository<AuthCode, String> {
}
