package com.walkhub.walkhub.domain.notice.domain.repository;

import com.walkhub.walkhub.domain.notice.domain.Notice;
import org.springframework.data.repository.CrudRepository;

public interface NoticeRepository extends CrudRepository<Notice, Long> {
}
