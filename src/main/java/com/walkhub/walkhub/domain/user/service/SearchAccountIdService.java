package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import com.walkhub.walkhub.domain.user.presentation.dto.response.UserAccountIdResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SearchAccountIdService {

    private final UserRepository userRepository;

    public UserAccountIdResponse execute(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return new UserAccountIdResponse(user.getAccountId());
    }
}
