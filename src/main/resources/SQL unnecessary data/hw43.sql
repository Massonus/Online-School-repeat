-- 1. sorted by last_name
SELECT *
FROM person
WHERE role = 'STUDENT'
ORDER BY last_name

-- 2. sorted by date before 2022 year
SELECT subject, lecture_date, COUNT(task)
FROM lecture
         INNER JOIN additional_material
                    ON additional_material.lecture_id = lecture.lecture_id
WHERE extract(year from lecture_date) < 2023
GROUP BY subject, lecture_date
ORDER BY lecture_date

-- 3. the earliest lecture with the most number of count_materials
SELECT subject, lecture_date, description, person_id, course_id, COUNT(task) AS count_materials
FROM lecture
         INNER JOIN additional_material
                    ON additional_material.lecture_id = lecture.lecture_id
WHERE extract(year from lecture_date) > 2023
GROUP BY subject, lecture_date, description, person_id, course_id
ORDER BY count_materials DESC, lecture_date LIMIT 1;

-- 4. count materials of URL, VIDEO and BOOK
SELECT COUNT(resource_type) AS video
FROM additional_material
WHERE resource_type='VIDEO'

-- bonus. names what don't start with 'N'
SELECT *
FROM person
WHERE first_name NOT LIKE 'N%';

-- 5. names what end until 'N'
SELECT *
FROM person
WHERE first_name < 'N'
ORDER BY first_name;

-- 6. first and last names with count_courses
SELECT first_name, last_name, count_courses
FROM person
ORDER BY count_courses, last_name

