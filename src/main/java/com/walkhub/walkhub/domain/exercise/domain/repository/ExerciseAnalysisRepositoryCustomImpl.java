package com.walkhub.walkhub.domain.exercise.domain.repository;

import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;
import static com.walkhub.walkhub.domain.school.domain.QSchool.school;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.exercise.domain.repository.vo.PrintExcelVo;
import com.walkhub.walkhub.domain.exercise.domain.repository.vo.QPrintExcelVo;
import com.walkhub.walkhub.global.enums.Authority;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExerciseAnalysisRepositoryCustomImpl implements ExerciseAnalysisRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<PrintExcelVo> getPrintExcelVoList(LocalDate startAt, LocalDate endAt, Authority authority, Integer grade, Integer classNum, Long schoolId) {

		BooleanBuilder builder = new BooleanBuilder();

		if (authority.equals(Authority.STUDENT)) {
			if (grade != null) {
				builder.and(section.grade.eq(grade));
			}
			if (classNum != null) {
				builder.and(section.classNum.eq(classNum));
			}
		}

		return queryFactory
			.select(new QPrintExcelVo(
				user.name,
				section.grade,
				section.classNum,
				user.number,
				exerciseAnalysis.walkCount.sum(),
				exerciseAnalysis.walkCount.avg().intValue(),
				exerciseAnalysis.distance.sum(),
				exerciseAnalysis.distance.avg().intValue()
			))
			.from(exerciseAnalysis)
			.join(exerciseAnalysis.user, user)
			.join(user.school, school)
			.join(user.section, section)
			.where(
				school.id.eq(schoolId),
				user.authority.eq(authority),
				exerciseAnalysis.date.between(startAt, endAt),
				builder
			)
			.groupBy(user)
			.fetch();
	}
}
