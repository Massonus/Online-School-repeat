CREATE TABLE public.lecture
(
    id           serial  NOT NULL,
    subject      text,
    description  text,
    teacher_id   integer NOT NULL DEFAULT 1,
    course_id    integer NOT NULL DEFAULT 1,
    lecture_date date    NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    CONSTRAINT teacher_id FOREIGN KEY (teacher_id)
        REFERENCES public.person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT course_id FOREIGN KEY (course_id)
        REFERENCES public.course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.lecture
    OWNER to postgres;