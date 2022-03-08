package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.exception.InvalidCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckClassCodeService {

    private final SectionRepository sectionRepository;

    public void execute(String code) {
        sectionRepository.findByClassCode(code)
                .orElseThrow(() -> InvalidCodeException.EXCEPTION);
    }

}
