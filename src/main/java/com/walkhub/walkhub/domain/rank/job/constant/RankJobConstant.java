package com.walkhub.walkhub.domain.rank.job.constant;

import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RankJobConstant {

    // common
    public static final Integer CHUNK_SIZE = 100;
    public static final String DATE_WEEK = DateType.WEEK.name();
    public static final String DATE_MONTH = DateType.MONTH.name();
    public static final String USER_SCOPE_CLASS = UserRankScope.CLASS.name();
    public static final String USER_SCOPE_SCHOOL = UserRankScope.SCHOOL.name();

    // school rank
    public static final String SCHOOL_RANK_JOB = "schoolRankJob";
    public static final String WEEK_SCHOOL_RANK_STEP = "querySchoolRankForLastWeekStep";
    public static final String MONTH_SCHOOL_RANK_STEP = "querySchoolRankForLastMonthStep";

    public static final String WEEK_SCHOOL_RANK_READER = "schoolRankForLastWeekReader";
    public static final String MONTH_SCHOOL_RANK_READER = "schoolRankForLastMonthReader";

    public static final String SELECT_SCHOOL_RANK_PROCEDURE = "SELECT_SCHOOL_RANK_BY_DATETYPE";
    public static final String SAVE_SCHOOL_RANK_PROCEDURE = "CALL SAVE_SCHOOL_RANK(:schoolId, :createdAt, :dateType, :name," +
            " :logoImageUrl, :userCount,:walkCount, :ranking)";

    // user rank
    public static final String USER_RANK_JOB = "userRankJob";
    public static final String WEEK_USER_SCHOOL_RANK_STEP = "weekUserSchoolRankStep";
    public static final String MONTH_USER_SCHOOL_RANK_STEP = "monthUserSchoolRankStep";
    public static final String WEEK_USER_CLASS_RANK_STEP = "weekUserClassRankStep";
    public static final String MONTH_USER_CLASS_RANK_STEP = "monthUserClassRankStep";

    public static final String WEEK_USER_SCHOOL_RANK_READER = "weekUserSchoolRankReader";
    public static final String MONTH_USER_SCHOOL_RANK_READER = "monthUserSchoolRankReader";
    public static final String WEEK_USER_CLASS_RANK_READER = "weekUserClassRankReader";
    public static final String MONTH_USER_CLASS_RANK_READER = "monthUserClassRankReader";

    public static final String SELECT_USER_RANK_BY_SCHOOL_PROCEDURE = "SELECT_USER_RANK_BY_SCHOOL";
    public static final String SELECT_USER_RANK_BY_CLASS_PROCEDURE = "SELECT_USER_RANK_BY_CLASS";
    public static final String SAVE_USER_RANK_PROCEDURE = "CALL SAVE_USER_RANK(:userId, :createdAt, :dateType, :scopeType," +
            " :schoolId, :name, :grade, :classNum, :profileImageUrl, :ranking, :walkCount)";

}
