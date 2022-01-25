package com.walkhub.walkhub.domain.notice.domain.repository;

import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.user.domain.School;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends CrudRepository<Notice, Long> {

	@Query("select n from Notice n where n.user.school = :school and n.scope = :scope")
	List<Notice> findAllBySchoolAndScope(@Param("school") School school, @Param("scope") Scope scope);

	List<Notice> findAllByScope(Scope scope);
}
