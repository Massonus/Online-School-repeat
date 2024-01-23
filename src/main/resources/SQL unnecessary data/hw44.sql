SELECT *
FROM lecture
ORDER BY time

SELECT *
FROM teacher

-- 1.
SELECT lecture_name,
       first_name,
       last_name
FROM lecture
         INNER JOIN teacher
                    ON teacher.id = lecture.teacher_id
ORDER BY time;

-- 2.
SELECT first_name, last_name, COUNT(*)
FROM lecture
         INNER JOIN teacher
                    ON teacher.id = lecture.teacher_id
GROUP BY first_name, last_name

-- 3.
SELECT last_name, lecture_name, lecture_date
FROM lecture
         INNER JOIN teacher
                    ON teacher.id = lecture.teacher_id
WHERE teacher.id = 3

-- 5.
SELECT extract(MONTH from lecture_date) AS month, COUNT(*)
FROM lecture
    INNER JOIN teacher
ON teacher.id = lecture.teacher_id
GROUP BY month

-- 6.
SELECT name, COUNT(*)
FROM homework
GROUP BY name
UNION
SELECT task, COUNT(*)
FROM additional_material
GROUP BY task