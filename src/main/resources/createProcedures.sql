-- 학교 랭크 조회 프로시저
CREATE PROCEDURE SELECT_SCHOOL_RANK_BY_DATETYPE(IN _DATETYPE ENUM ('WEEK', 'MONTH'))
BEGIN
DECLARE _STARTDATE DATE;
DECLARE _ENDDATE DATE;

IF _DATETYPE = 'WEEK' THEN
    SET _STARTDATE = (SELECT IF(WEEKDAY(someday) != 0,
                              DATE_SUB(someday, INTERVAL WEEKDAY(someday) DAY),
                              someday) AS lastMonDay
                      FROM (SELECT DATE_SUB(CURRENT_DATE, INTERVAL 1 WEEK) AS someday) AS t);
    SET _ENDDATE = (SELECT IF(WEEKDAY(someday) != 6,
                            IF(WEEKDAY(someday) > 6,
                                DATE_SUB(someday, INTERVAL WEEKDAY(someday)-6 DAY),
                                DATE_ADD(someday, INTERVAL 6-WEEKDAY(someday) DAY)),
                            someday) AS lastSunDay
                    FROM (SELECT DATE_SUB(CURRENT_DATE, INTERVAL 1 WEEK) AS someday) AS t);
ELSEIF _DATETYPE = 'MONTH' THEN
	SET _STARTDATE = LAST_DAY(CURRENT_DATE - INTERVAL 2 MONTH) + INTERVAL 1 DAY;
    SET _ENDDATE = LAST_DAY(CURRENT_DATE - INTERVAL 1 MONTH);
END IF;

SELECT school.id AS school_id,
       school.name,
       school.logo_image_url,
       school.user_count,
       IFNULL(SUM(exercise_analysis.walk_count), 0) AS walk_count,
       ROW_NUMBER() OVER (ORDER BY SUM(exercise_analysis.walk_count) DESC) AS ranking
FROM school
         LEFT JOIN user ON school.id = user.school_id
         LEFT JOIN exercise_analysis ON exercise_analysis.user_id = user.id
WHERE exercise_analysis.date BETWEEN _STARTDATE AND _ENDDATE
GROUP BY school.id, school.name, school.logo_image_url, school.user_count;
END$$

-- 학교 랭크 저장 프로시저
CREATE PROCEDURE SAVE_SCHOOL_RANK(IN _SCHOOLID BIGINT,
                                  IN _CREATEDAT DATE,
                                  IN _DATETYPE ENUM ('WEEK', 'MONTH'),
                                  IN _NAME VARCHAR(20),
                                  IN _LOGOIMAGEURL VARCHAR(255),
                                  IN _USERCOUNT BIGINT,
                                  IN _WALKCOUNT INT,
                                  IN _RANKING INT)
BEGIN

IF (SELECT COUNT(*) FROM school_rank
    WHERE school_id = _SCHOOLID
		AND created_at = _CREATEDAT
		AND date_type = _DATETYPE) = 0 THEN
    INSERT INTO school_rank (school_id, created_at, date_type, name, logo_image_url, user_count, walk_count, ranking)
    VALUES (_SCHOOLID, _CREATEDAT, _DATETYPE, _NAME, _LOGOIMAGEURL, _USERCOUNT, _WALKCOUNT, _RANKING);
ELSE
UPDATE school_rank SET ranking = _RANKING, walk_count = _WALKCOUNT
WHERE school_id = _SCHOOLID
  AND created_at = _CREATEDAT
  AND date_type = _DATETYPE;
END IF;
END $$

-- 유저 랭크 날짜, 반 별로 조회하는 프로시저
CREATE PROCEDURE SELECT_USER_RANK_BY_CLASS(IN date_type_in INT)
BEGIN
SELECT user.name,
       user.school_id,
       section.grade,
       section.class_num,
       user.profile_image_url,
       user.id   user_id,
       IFNULL(SUM(exercise_analysis.walk_count), 0) AS walk_count,
       ROW_NUMBER() OVER (PARTITION BY user.school_id, section.class_num, section.grade ORDER BY SUM(exercise_analysis.walk_count) DESC, user.name ASC) AS ranking
FROM user
         LEFT JOIN section ON section.id = user.section_id
         LEFT JOIN exercise_analysis ON exercise_analysis.user_id = user.id
WHERE DATEDIFF(NOW(), exercise_analysis.date) < date_type_in
GROUP BY user.id, user.name, user.school_id, user.profile_image_url, section.grade, section.class_num;
END$$

-- 유저 랭크 날짜, 학교별로 조회하는 프로시저
CREATE PROCEDURE SELECT_USER_RANK_BY_SCHOOL(IN date_type_in INT)
BEGIN
SELECT user.name,
       user.school_id,
       section.grade,
       section.class_num,
       user.profile_image_url,
       user.id   user_id,
       IFNULL(SUM(exercise_analysis.walk_count), 0) AS walk_count,
       ROW_NUMBER() OVER (PARTITION BY user.school_id ORDER BY SUM(exercise_analysis.walk_count) DESC, user.name ASC) AS ranking
FROM user
         LEFT JOIN section ON section.id = user.section_id
         LEFT JOIN exercise_analysis ON exercise_analysis.user_id = user.id
WHERE DATEDIFF(NOW(), exercise_analysis.date) < date_type_in
GROUP BY user.id, user.name, user.school_id, user.profile_image_url, section.grade, section.class_num;
END$$

-- 유저 랭크 저장 프로시저
CREATE PROCEDURE SAVE_USER_RANK(IN user_id_in VARCHAR(255),
                                IN created_at_in DATE,
                                IN date_type_in ENUM ('WEEK', 'MONTH'),
                                IN scope_type_in ENUM ('SCHOOL', 'CLASS'),
                                IN school_id_in VARCHAR(7),
                                IN name_in VARCHAR(10),
                                IN grade_in INT,
                                IN class_num_in INT,
                                IN profile_image_url_in VARCHAR(255),
                                IN ranking_in INT,
                                IN walk_count_in INT)
BEGIN

IF (SELECT count(*)
    FROM user_rank
    WHERE user_id = user_id_in
      AND created_at = created_at_in
      AND date_type = date_type_in
      AND scope_type = scope_type_in) = 0 THEN
    INSERT INTO user_rank (user_id, created_at, date_type, scope_type, school_id, name, grade, class_num,
                           profile_image_url, ranking, walk_count)
    VALUES (user_id_in, created_at_in, date_type_in, scope_type_in, school_id_in, name_in, grade_in, class_num_in,
        profile_image_url_in, ranking_in, walk_count_in);
ELSE
UPDATE user_rank
SET ranking    = ranking_in,
    walk_count = walk_count_in
WHERE account_id = user_id_in
  AND created_at = created_at_in
  AND date_type = date_type_in
  AND scope_type = scope_type_in;
END IF;
END $$
