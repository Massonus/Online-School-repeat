CREATE TABLE public.homework
(
    id         serial  NOT NULL,
    task       text,
    lecture_id integer NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    CONSTRAINT lecture_id FOREIGN KEY (lecture_id)
        REFERENCES public.lecture (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.homework
    OWNER to postgres;