CREATE TABLE public.additional_material
(
    id serial NOT NULL,
    task text,
    resource_type text NOT NULL DEFAULT 'BOOK',
    course_id integer NOT NULL DEFAULT 1,
    lecture_id integer NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    CONSTRAINT course_id FOREIGN KEY (course_id)
        REFERENCES public.course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT lecture_id FOREIGN KEY (lecture_id)
        REFERENCES public.lecture (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.additional_material
    OWNER to postgres;