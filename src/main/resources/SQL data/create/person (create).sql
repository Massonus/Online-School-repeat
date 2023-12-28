CREATE TABLE public.person
(
    id serial NOT NULL,
    first_name text,
    last_name text,
    phone text,
    email text,
    course_id integer NOT NULL DEFAULT 1,
    role text NOT NULL DEFAULT 'STUDENT',
    PRIMARY KEY (id),
    CONSTRAINT course_id FOREIGN KEY (course_id)
        REFERENCES public.course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.person
    OWNER to postgres;