SELECT *
FROM lecture
ORDER BY lecture_date

SELECT *
FROM person

-- 1.
SELECT subject,
       first_name,
       last_name
FROM lecture
         INNER JOIN person
                    ON person.person_id = lecture.person_id
ORDER BY lecture_date;

-- 2.
SELECT first_name, last_name, COUNT(*)
FROM lecture
         INNER JOIN person
                    ON person.person_id = lecture.person_id
GROUP BY first_name, last_name

-- 3.
SELECT last_name, subject, lecture_date
FROM lecture
         INNER JOIN person
                    ON person.person_id = lecture.person_id
WHERE person.person_id = 3

-- 5.
SELECT extract(MONTH from lecture_date) AS month, COUNT(*)
FROM lecture
    INNER JOIN person
ON person.person_id = lecture.person_id
GROUP BY month

-- 6.
SELECT task, COUNT(*)
FROM homework
GROUP BY task
UNION
SELECT task, COUNT(*)
FROM additional_material
GROUP BY task