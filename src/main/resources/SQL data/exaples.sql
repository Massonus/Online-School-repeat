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
FROM test_lectures

SELECT name, COUNT(*) as count
FROM test_lectures
WHERE name = 'It'
GROUP BY name
HAVING COUNT (*) > 1;

SELECT foo.id
FROM (SELECT id, name, lecture_date FROM test_lectures) AS foo

SELECT *
FROM test_lectures
WHERE id IN (SELECT id FROM lecture);

SELECT *
FROM test_lectures
WHERE id > ALL (SELECT id FROM lecture);

SELECT *
FROM test_lectures
WHERE id > ANY (SELECT id FROM lecture);

SELECT *
FROM lecture