-- 1. sorted by last_name
SELECT * FROM student
ORDER BY last_name;

-- 2. sorted by date before 2022 year
SELECT name, count_materials, time FROM lecture
WHERE extract(year from time) < 2023
ORDER BY time;

-- 3. the earliest lecture with the most number of count_materials
SELECT * FROM lecture
ORDER BY count_materials DESC, time LIMIT 1;

-- 4. count materials of URL, VIDEO and BOOK
SELECT COUNT(url) as URL, COUNT(video) as VIDEO, COUNT(book) as BOOK
FROM additional_material;

-- bonus. names what don't start with 'N'
SELECT * FROM teacher
WHERE first_name NOT LIKE 'N%';

-- 5. names what end until 'N'
SELECT * FROM teacher
WHERE first_name < 'N'
ORDER BY first_name;

-- 6. first and last names with count_courses
SELECT first_name, last_name, count_courses FROM student
ORDER BY count_courses, last_name

