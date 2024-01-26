SELECT *
FROM test_lectures
ORDER BY count_materials DESC

SELECT *
FROM test_lectures
ORDER BY name, count_materials;

SELECT *
FROM test_lectures
WHERE extract(year from lecture_date) < 2023
ORDER BY count_materials DESC LIMIT 1

SELECT count(id) AS br, count(count_materials) AS cm
FROM test_lectures

SELECT min(lecture_date)
FROM lecture

SELECT subject, COUNT(*) as count
FROM lecture
WHERE subject = 'Geography'
GROUP BY subject
HAVING COUNT (*) > 1;

SELECT foo.lecture_id
FROM (SELECT lecture_id, subject, lecture_date FROM lecture) AS foo

SELECT *
FROM test_lectures
WHERE lecture_id IN (SELECT lecture_id FROM lecture);

SELECT *
FROM test_lectures
WHERE lecture_id > ALL (SELECT lecture_id FROM lecture);

SELECT *
FROM test_lectures
WHERE lecture_id > ANY (SELECT lecture_id FROM lecture);

SELECT *
FROM lecture