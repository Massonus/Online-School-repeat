INSERT INTO public.course(
    name)
VALUES
    ('First'),
    ('Second'),
    ('Third'),
    ('Fourth');

INSERT INTO public.student(
    name, course_id)
VALUES
    ('Mark', 2),
    ('John', 1),
    ('Nick', 3),
    ('Indo', 4);

INSERT INTO public.teacher(
    name, subji)
VALUES
    ('Tim', 'Geo'),
    ('Johnson', 'Math'),
    ('Leroy', 'Literature'),
    ('Lor', 'Programming');

INSERT INTO public.lecture(
    name)
VALUES
    ('Math'),
    ('Geo'),
    ('Literature'),
    ('Programming');

INSERT INTO public.additional_material(
    name, task)
VALUES
    ('Geo', 'read the book'),
    ('Math', 'read the url'),
    ('Literature', 'read the book'),
    ('Programming', 'watch YouTube');

