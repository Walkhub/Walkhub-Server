package com.walkhub.walkhub.domain.notice.domain.repository;

import com.walkhub.walkhub.domain.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
