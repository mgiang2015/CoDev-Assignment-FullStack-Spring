-- uses public schema. Create if doesn't exist
CREATE SCHEMA IF NOT EXISTS public;

-- Drop all tables
DROP TABLE IF EXISTS people CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS author_books CASCADE;
DROP TABLE IF EXISTS book_rents CASCADE;

-- PUT THIS IN README - MUST SET UP BEFORE RUNNING, spring.datasource.url= jdbc:postgresql://localhost:5432/mydb, spring.datasource.username= min, spring.datasource.password= 123
CREATE SEQUENCE IF NOT EXISTS people_id_seq;
CREATE SEQUENCE IF NOT EXISTS books_id_seq;
CREATE SEQUENCE IF NOT EXISTS authors_id_seq;

--this is the people table
CREATE TABLE IF NOT EXISTS people
(
    id integer NOT NULL DEFAULT nextval('people_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    country_id bigint,
    CONSTRAINT people_pkey PRIMARY KEY (id)
);
--this is the book table
CREATE TABLE IF NOT EXISTS books
(
    id integer NOT NULL DEFAULT nextval('books_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (id)
);
-- this is the author table
CREATE TABLE IF NOT EXISTS authors
(
    id integer NOT NULL DEFAULT nextval('authors_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    CONSTRAINT authors_pkey PRIMARY KEY (id)
);
-- this is the author and book multi to multi relationship table
CREATE TABLE IF NOT EXISTS author_books
(
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    author_id integer NOT NULL,
    book_id integer NOT NULL,
    CONSTRAINT author_books_pkey PRIMARY KEY (author_id, book_id),
    CONSTRAINT author_books_author_id_fkey FOREIGN KEY (author_id)
        REFERENCES public.authors (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT author_books_book_id_fkey FOREIGN KEY (book_id)
        REFERENCES public.books (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
--this is the book renting history table
CREATE TABLE IF NOT EXISTS book_rents
(
    person_id bigint NOT NULL,
    book_id bigint NOT NULL,
    "createdAt" time with time zone NOT NULL,
    "updatedAt" time with time zone NOT NULL
);

-- Populate table with some data entries
INSERT INTO public.people (id, name, "createdAt", "updatedAt", country_id)
VALUES(1, 'Jesse Pinkman', '2023-03-03 15:49:00', '2023-03-03 15:49:00', 0);

INSERT INTO public.people (id, name, "createdAt", "updatedAt", country_id)
VALUES(2, 'Walt White', '2023-03-03 15:49:00', '2023-03-03 15:49:00', 1);

INSERT INTO public.people (id, name, "createdAt", "updatedAt", country_id)
VALUES(3, 'Gus Fring', '2023-03-03 15:49:00', '2023-03-03 15:49:00', 0);

INSERT INTO public.people (id, name, "createdAt", "updatedAt", country_id)
VALUES(4, 'Hank Schrader', '2023-03-03 15:49:00', '2023-03-03 15:49:00', 1);

INSERT INTO public.people (id, name, "createdAt", "updatedAt", country_id)
VALUES(5, 'Skyler White', '2023-03-03 15:49:00', '2023-03-03 15:49:00', 1);

INSERT INTO public.people (id, name, "createdAt", "updatedAt", country_id)
VALUES(6, 'Heisenberg', '2023-03-03 15:49:00', '2023-03-03 15:49:00', 0);

INSERT INTO public.people (id, name, "createdAt", "updatedAt", country_id)
VALUES(7, 'Saul Goodman', '2023-03-03 15:49:00', '2023-03-03 15:49:00', 0);

INSERT INTO public.people (id, name, "createdAt", "updatedAt", country_id)
VALUES(8, 'Kanye West', '2023-03-03 15:49:00', '2023-03-03 15:49:00', 1);

INSERT INTO public.authors (id, name, "createdAt", "updatedAt")
VALUES(1, 'Barack Obama', '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.authors (id, name, "createdAt", "updatedAt")
VALUES(2, 'Michelle Obama', '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.authors (id, name, "createdAt", "updatedAt")
VALUES(3, 'Dr. Seuss', '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.books (id, name, "createdAt", "updatedAt")
VALUES(1, 'Harry Potter', '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.books (id, name, "createdAt", "updatedAt")
VALUES(2, 'Lookism', '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.books (id, name, "createdAt", "updatedAt")
VALUES(3, 'Breaking Bad', '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.books (id, name, "createdAt", "updatedAt")
VALUES(4, 'Saul', '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.author_books ("createdAt", "updatedAt", author_id, book_id)
VALUES('2023-03-03 15:49:00', '2023-03-03 15:49:00', 1, 1);

INSERT INTO public.author_books ("createdAt", "updatedAt", author_id, book_id)
VALUES('2023-03-03 15:49:00', '2023-03-03 15:49:00', 1, 2);

INSERT INTO public.author_books ("createdAt", "updatedAt", author_id, book_id)
VALUES('2023-03-03 15:49:00', '2023-03-03 15:49:00', 2, 3);

INSERT INTO public.author_books ("createdAt", "updatedAt", author_id, book_id)
VALUES('2023-03-03 15:49:00', '2023-03-03 15:49:00', 3, 4);

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(1, 1, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(1, 2, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(1, 3, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(1, 4, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(2, 2, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(2, 3, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(2, 4, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(3, 3, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(3, 4, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(4, 4, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(5, 1, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(5, 2, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(5, 3, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(5, 4, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(6, 2, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(6, 3, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(6, 4, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(7, 3, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(7, 4, '2023-03-03 15:49:00', '2023-03-03 15:49:00');

INSERT INTO public.book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(8, 4, '2023-03-03 15:49:00', '2023-03-03 15:49:00');