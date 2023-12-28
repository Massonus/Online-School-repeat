CREATE DATABASE "Online_School"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


CREATE TABLE public.course
(
    id serial NOT NULL,
    name text,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.course
    OWNER to postgres;


CREATE TABLE public.student
(
    id serial NOT NULL,
    name text,
    course_id integer,
    PRIMARY KEY (id),
    CONSTRAINT course_id FOREIGN KEY (course_id)
        REFERENCES public.course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.student
    OWNER to postgres;


CREATE TABLE public.teacher
(
    id serial NOT NULL,
    name text,
    subji text,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.teacher
    OWNER to postgres;


CREATE TABLE public.lecture
(
    id serial NOT NULL,
    name text,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.lecture
    OWNER to postgres;

CREATE TABLE public.additional_material
(
    id serial NOT NULL,
    name text,
    task text,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.additional_material
    OWNER to postgres;