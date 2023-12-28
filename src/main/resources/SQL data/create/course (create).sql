CREATE TABLE public.course
(
    id serial NOT NULL,
    course_name text DEFAULT 'NIO',
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.course
    OWNER to postgres;