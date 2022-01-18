package com.walkhub.walkhub.domain.notice.domain.repository;

import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.user.domain.School;
import com.walkhub.walkhub.global.enums.Authority;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

	@Query("select n from Notice n where n.scope = com.walkhub.walkhub.domain.notice.domain.type.Scope.ALL or (n.user.school = :school and (n.scope = com.walkhub.walkhub.domain.notice.domain.type.Scope.STU or :authority <> com.walkhub.walkhub.global.enums.Authority.USER))")
	List<Notice> findAllBy(@Param("school") School school, @Param("authority") Authority authority);

}
