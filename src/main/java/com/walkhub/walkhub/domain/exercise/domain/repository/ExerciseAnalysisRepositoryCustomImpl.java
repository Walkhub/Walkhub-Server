package com.walkhub.walkhub.domain.exercise.domain.repository;

import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;
import static com.walkhub.walkhub.domain.school.domain.QSchool.school;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.excel.presentation.dto.request.PrintExcelRequest;
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
	public List<PrintExcelVo> getPrintExcelVoList(PrintExcelRequest excelRequest, Long schoolId) {

		 LocalDate startAt = excelRequest.getStartAt();

		 LocalDate endAt = excelRequest.getEndAt();

		 Authority authority = excelRequest.getAuthority();

		 Integer grade = excelRequest.getGrade();

		 Integer classNum = excelRequest.getGrade();

		return queryFactory
			.select(new QPrintExcelVo(
				user.name,
				section.grade,
				section.classNum,
				user.number,
				exerciseAnalysis.walkCount.sum(),
				exerciseAnalysis.walkCount.avg().intValue(),
				exerciseAnalysis.distance.sum(),
				exerciseAnalysis.distance.avg().intValue(),
				user.authority
			))
			.from(exerciseAnalysis)
			.join(exerciseAnalysis.user, user)
			.join(user.school, school)
			.join(user.section, section)
			.where(
				school.id.eq(schoolId),
				exerciseAnalysis.date.between(startAt, endAt),
				nullFilter(authority,grade,classNum)
			)
			.groupBy(user.authority)
			.fetch();
	}

	private BooleanBuilder nullFilter(Authority authority, Integer grade, Integer classNum) {

		BooleanBuilder builder = new BooleanBuilder();

		if (authority != null) {
			builder.and(user.authority.eq(authority));

			if (authority.equals(Authority.USER)) {
				if (grade != null) {
					builder.and(section.grade.eq(grade));
				}
				if (classNum != null) {
					builder.and(section.classNum.eq(classNum));
				}
			}
		}
		return builder;
	}
}
