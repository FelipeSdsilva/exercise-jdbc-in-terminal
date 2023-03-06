-- Database: coursejdbc

-- DROP DATABASE IF EXISTS coursejdbc;

CREATE DATABASE coursejdbc
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Table: public.department

-- DROP TABLE IF EXISTS public.department;

CREATE TABLE IF NOT EXISTS public.department
(
    id integer NOT NULL DEFAULT nextval('department_id_seq'::regclass),
    name character varying(60) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    CONSTRAINT department_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.department
    OWNER to postgres;
-- Table: public.seller

-- DROP TABLE IF EXISTS public.seller;

CREATE TABLE IF NOT EXISTS public.seller
(
    id integer NOT NULL DEFAULT nextval('seller_id_seq'::regclass),
    name character varying(60) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    birthdate timestamp without time zone NOT NULL,
    basesalary double precision NOT NULL,
    departmentid integer NOT NULL,
    CONSTRAINT seller_pkey PRIMARY KEY (id),
    CONSTRAINT seller_departmentid_fkey FOREIGN KEY (departmentid)
        REFERENCES public.department (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.seller
    OWNER to postgres;

INSERT INTO department (Name) VALUES
  ('Computers'),
  ('Electronics'),
  ('Fashion'),
  ('Books');

INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2);
