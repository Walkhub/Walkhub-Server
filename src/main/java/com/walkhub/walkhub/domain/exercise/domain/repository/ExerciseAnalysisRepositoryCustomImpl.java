package com.walkhub.walkhub.domain.exercise.domain.repository;

import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;
import static com.walkhub.walkhub.domain.school.domain.QSchool.school;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.excel.domain.type.UserType;
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
		UserType userType = excelRequest.getUserType();
		Integer grade = excelRequest.getGrade();
		Integer classNum = excelRequest.getClassNum();

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
				user.authority,
				school.name
			))
			.from(exerciseAnalysis)
			.join(exerciseAnalysis.user, user)
			.join(user.school, school)
			.leftJoin(user.section, section)
			.where(
				school.id.eq(schoolId),
				exerciseAnalysis.date.between(startAt, endAt),
				userTypeFilter(userType, grade, classNum)
			)
			.groupBy(user)
			.fetch();
	}

	private BooleanBuilder userTypeFilter(UserType userType, Integer grade, Integer classNum) {
		BooleanBuilder builder = new BooleanBuilder();

		switch (userType) {
			case STUDENT: {
				builder.and(user.authority.eq(Authority.USER));
				builder.and(nullFilter(builder, grade, classNum));
				break;
			}
			case TEACHER: {
				builder.and(user.authority.eq(Authority.TEACHER));
				break;
			}
			case ALL: {
				builder.and(user.authority.eq(Authority.USER).or(
					user.authority.eq(Authority.TEACHER)
				));
			}
		}

		return builder;
	}

	private BooleanBuilder nullFilter(BooleanBuilder builder, Integer grade, Integer classNum) {
		if (grade != null) {
			builder.and(section.grade.eq(grade));
		}

		if (classNum != null) {
			builder.and(section.classNum.eq(classNum));
		}

		return builder;
	}
}
