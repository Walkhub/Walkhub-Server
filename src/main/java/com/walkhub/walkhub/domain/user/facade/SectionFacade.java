package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SectionFacade {

    private final SectionRepository sectionRepository;

    public Section getSectionById(Long id) {
       return sectionRepository.findById(id)
                .orElseThrow(() -> GroupNotFoundException.EXCEPTION);
    }
}
