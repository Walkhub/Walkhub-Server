package com.walkhub.walkhub.domain.rank.job.constant;

public final class RankJobConstant {

    // common
    public static final Integer CHUNK_SIZE = 100;
    public static final String DATE_WEEK = "WEEK";
    public static final String DATE_MONTH = "MONTH";

    // school rank
    public static final String SCHOOL_RANK_JOB = "schoolRankJob";
    public static final String WEEK_SCHOOL_RANK_STEP = "querySchoolRankForLastWeekStep";
    public static final String MONTH_SCHOOL_RANK_STEP = "querySchoolRankForLastMonthStep";
    public static final String WEEK_SCHOOL_RANK_READER = "schoolRankForLastWeekReader";
    public static final String MONTH_SCHOOL_RANK_READER = "schoolRankForLastMonthReader";

    public static final String SELECT_PROCEDURE_NAME = "SELECT_SCHOOL_RANK_BY_DATETYPE";
    public static final String SQL_SAVE_SCHOOL_RANK = "CALL SAVE_SCHOOL_RANK(:schoolId, :createdAt, :dateType, :name, :logoImageUrl, :userCount,:walkCount, :ranking)";

}
