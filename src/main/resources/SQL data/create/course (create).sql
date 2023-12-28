CREATE TABLE public.course
(
    id serial NOT NULL,
    course_name text,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.course
    OWNER to postgres;