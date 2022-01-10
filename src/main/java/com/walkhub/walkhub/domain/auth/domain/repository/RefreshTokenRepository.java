package com.walkhub.walkhub.domain.auth.domain.repository;

import com.walkhub.walkhub.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
