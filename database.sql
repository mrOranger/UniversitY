--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1 (Debian 15.1-1.pgdg110+1)
-- Dumped by pg_dump version 15.1

-- Started on 2022-12-10 10:50:41 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 35705)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id integer NOT NULL,
    number integer NOT NULL,
    street character varying NOT NULL,
    city character varying NOT NULL,
    province character varying,
    region character varying,
    nation character varying
);


ALTER TABLE public.address OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 35704)
-- Name: Address_Id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Address_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Address_Id_seq" OWNER TO postgres;

--
-- TOC entry 3402 (class 0 OID 0)
-- Dependencies: 215
-- Name: Address_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Address_Id_seq" OWNED BY public.address.id;


--
-- TOC entry 222 (class 1259 OID 35803)
-- Name: exam; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exam (
    id integer NOT NULL,
    vote integer,
    vate date NOT NULL,
    course character varying NOT NULL,
    student character varying NOT NULL
);


ALTER TABLE public.exam OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 35802)
-- Name: Exam_Id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Exam_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Exam_Id_seq" OWNER TO postgres;

--
-- TOC entry 3403 (class 0 OID 0)
-- Dependencies: 221
-- Name: Exam_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Exam_Id_seq" OWNED BY public.exam.id;


--
-- TOC entry 220 (class 1259 OID 35795)
-- Name: course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course (
    id character varying NOT NULL,
    "startDate" date
);


ALTER TABLE public.course OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 35751)
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    name character varying NOT NULL,
    address integer NOT NULL,
    faculty character varying NOT NULL
);


ALTER TABLE public.department OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 35720)
-- Name: faculty; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.faculty (
    name character varying NOT NULL,
    address integer NOT NULL
);


ALTER TABLE public.faculty OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 35821)
-- Name: join_course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.join_course (
    professor character varying NOT NULL,
    course character varying NOT NULL
);


ALTER TABLE public.join_course OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 35838)
-- Name: join_department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.join_department (
    student character varying NOT NULL,
    department character varying NOT NULL
);


ALTER TABLE public.join_department OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 35768)
-- Name: professor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.professor (
    id character varying NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    "dateOfBirth" character varying NOT NULL,
    address integer NOT NULL,
    department character varying,
    director character varying,
    president character varying
);


ALTER TABLE public.professor OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 35696)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    date_of_birth date NOT NULL,
    diploma_grade integer DEFAULT 60 NOT NULL,
    bachelor_grade integer,
    address integer NOT NULL,
    id character varying(20) NOT NULL,
    name character varying(30) NOT NULL,
    surname character varying(30) NOT NULL
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 3210 (class 2604 OID 35708)
-- Name: address id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address ALTER COLUMN id SET DEFAULT nextval('public."Address_Id_seq"'::regclass);


--
-- TOC entry 3211 (class 2604 OID 35806)
-- Name: exam id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam ALTER COLUMN id SET DEFAULT nextval('public."Exam_Id_seq"'::regclass);


--
-- TOC entry 3388 (class 0 OID 35705)
-- Dependencies: 216
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (id, number, street, city, province, region, nation) FROM stdin;
1	1	Via Nazionale	Milano	Milano	Lombardia	Italia
2	2	Via Nazionale	Torino	Torino	Piemonte	Italia
3	3	Via Nazionale	Bari	Bari	Puglia	Italia
4	4	Via Nazionale	Palermo	Palermo	Sicilia	Italia
5	5	Main Street	New York	\N	\N	USA
6	6	Dam Square	Amsterdam	\N	\N	Holland
\.


--
-- TOC entry 3392 (class 0 OID 35795)
-- Dependencies: 220
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.course (id, "startDate") FROM stdin;
Analisi 1	2022-01-01
Analisi 2	\N
Fisica 1	2022-06-01
Fisica 2	2022-09-11
Lettere 1	2022-03-01
Lettere 2	\N
\.


--
-- TOC entry 3390 (class 0 OID 35751)
-- Dependencies: 218
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.department (name, address, faculty) FROM stdin;
Ingegneria Informatica	1	Ingegneria
Ingegneria Meccanica	2	Ingegneria
Matematica	4	IMF
Fisica	4	IMF
Lettere	6	LESU
Filosofia	6	LESU
Scienze Politiche	6	LESU
Ingegneria Gestionale	3	Ingegneria
\.


--
-- TOC entry 3394 (class 0 OID 35803)
-- Dependencies: 222
-- Data for Name: exam; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.exam (id, vote, vate, course, student) FROM stdin;
1	\N	2022-01-10	Analisi 1	AB123
2	18	2022-02-11	Analisi 1	AB123
3	28	2022-02-15	Analisi 2	CD123
4	22	2022-02-18	Analisi 2	CD123
5	29	2022-02-11	Analisi 1	EF123
6	30	2022-02-18	Analisi 2	EF123
7	\N	2022-02-18	Fisica 1	EF123
8	\N	2022-03-02	Fisica 2	EF123
9	28	2022-01-28	Lettere 1	GH123
10	\N	2022-02-23	Lettere 2	GH123
11	22	2022-01-28	Lettere 1	KL123
12	18	2022-02-23	Lettere 2	KL123
13	27	2022-01-28	Lettere 1	MN123
14	30	2022-02-23	Lettere 2	MN123
15	17	2022-01-28	Lettere 1	OP123
16	\N	2022-02-23	Lettere 2	OP123
17	15	2022-01-28	Lettere 1	QR123
18	\N	2022-02-23	Lettere 2	QR123
19	15	2022-01-28	Lettere 1	ST123
20	\N	2022-02-23	Lettere 2	ST123
21	17	2022-02-18	Analisi 2	AB123
22	21	2022-02-18	Analisi 2	AB123
\.


--
-- TOC entry 3389 (class 0 OID 35720)
-- Dependencies: 217
-- Data for Name: faculty; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.faculty (name, address) FROM stdin;
Ingegneria	1
IMF	2
LESU	3
\.


--
-- TOC entry 3395 (class 0 OID 35821)
-- Dependencies: 223
-- Data for Name: join_course; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.join_course (professor, course) FROM stdin;
123AB	Analisi 1
123AB	Analisi 2
123CD	Analisi 1
123CD	Fisica 1
123EF	Fisica 1
123GH	Fisica 2
123IJ	Analisi 2
123IJ	Fisica 1
123LM	Analisi 1
123LM	Fisica 2
123PQ	Lettere 1
123RS	Lettere 1
123TU	Lettere 2
\.


--
-- TOC entry 3396 (class 0 OID 35838)
-- Dependencies: 224
-- Data for Name: join_department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.join_department (student, department) FROM stdin;
AB123	Ingegneria Informatica
CD123	Ingegneria Informatica
EF123	Ingegneria Meccanica
GH123	Ingegneria Gestionale
IJ123	Fisica
KL123	Lettere
MN123	Lettere
OP123	Filosofia
QR123	Scienze Politiche
ST123	Scienze Politiche
\.


--
-- TOC entry 3391 (class 0 OID 35768)
-- Dependencies: 219
-- Data for Name: professor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.professor (id, name, surname, "dateOfBirth", address, department, director, president) FROM stdin;
123AB	Mario	Rossi	1970-01-01	1	Ingegneria Informatica	Ingegneria Informatica	Ingegneria
123CD	Maria	Verdi	1975-11-21	2	Ingegneria Gestionale	Ingegneria Gestionale	\N
123EF	Marco	Bruni	1967-09-11	2	Ingegneria Gestionale	Ingegneria Informatica	\N
123GH	Adriano	Gialli	1982-06-12	2	Ingegneria Meccanica	\N	\N
123IJ	Adriano	Bianchi	1972-10-02	3	Fisica	Fisica	IMF
123LM	Adriana	Bruni	1987-03-23	3	Matematica	Matematica	\N
123NO	Adrian	Gialli	1981-09-11	3	Matematica	\N	\N
123PQ	Emma	Rossi	1971-10-02	2	Lettere	Lettere	LESU
123RS	Ernesto	Bruni	1967-03-23	3	Filosofia	Filosofia	\N
123TU	John	Snow	1986-09-11	4	Scienze Politiche	Scienze Politiche	\N
\.


--
-- TOC entry 3386 (class 0 OID 35696)
-- Dependencies: 214
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (date_of_birth, diploma_grade, bachelor_grade, address, id, name, surname) FROM stdin;
1991-01-01	89	\N	1	AB123	Mario	Rossi
1998-11-09	87	\N	2	CD123	Federica	Verdi
1992-12-01	89	100	3	EF123	Adriano	Neri
1996-04-04	98	102	4	GH123	Michelangelo	Bruni
1992-06-21	98	\N	4	IJ123	Maria	Bruni
1994-09-21	67	100	5	KL123	Mary	Black
1991-02-17	98	110	5	MN123	Edward	Black
1997-02-21	98	100	6	OP123	Hans	Von Gogh
1995-10-21	67	104	6	QR123	Betty	Van Dick
1990-01-09	78	100	6	ST123	Jenny	Arjen
\.


--
-- TOC entry 3404 (class 0 OID 0)
-- Dependencies: 215
-- Name: Address_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Address_Id_seq"', 1, false);


--
-- TOC entry 3405 (class 0 OID 0)
-- Dependencies: 221
-- Name: Exam_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Exam_Id_seq"', 1, false);


--
-- TOC entry 3215 (class 2606 OID 35712)
-- Name: address Address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT "Address_pkey" PRIMARY KEY (id);


--
-- TOC entry 3223 (class 2606 OID 35801)
-- Name: course Course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT "Course_pkey" PRIMARY KEY (id);


--
-- TOC entry 3225 (class 2606 OID 35810)
-- Name: exam Exam_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT "Exam_pkey" PRIMARY KEY (id);


--
-- TOC entry 3227 (class 2606 OID 35827)
-- Name: join_course Join_Course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.join_course
    ADD CONSTRAINT "Join_Course_pkey" PRIMARY KEY (professor, course);


--
-- TOC entry 3229 (class 2606 OID 35844)
-- Name: join_department Join_Department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.join_department
    ADD CONSTRAINT "Join_Department_pkey" PRIMARY KEY (student, department);


--
-- TOC entry 3219 (class 2606 OID 35757)
-- Name: department department_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pk PRIMARY KEY (name) INCLUDE (name);


--
-- TOC entry 3217 (class 2606 OID 35726)
-- Name: faculty faculty_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.faculty
    ADD CONSTRAINT faculty_pk PRIMARY KEY (name) INCLUDE (name);


--
-- TOC entry 3221 (class 2606 OID 35774)
-- Name: professor professor_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT professor_pk PRIMARY KEY (id) INCLUDE (id);


--
-- TOC entry 3213 (class 2606 OID 35719)
-- Name: student student_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pk PRIMARY KEY (id) INCLUDE (id);


--
-- TOC entry 3230 (class 2606 OID 35713)
-- Name: student address_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT address_fk FOREIGN KEY (address) REFERENCES public.address(id) NOT VALID;


--
-- TOC entry 3231 (class 2606 OID 35727)
-- Name: faculty address_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.faculty
    ADD CONSTRAINT address_fk FOREIGN KEY (address) REFERENCES public.address(id);


--
-- TOC entry 3232 (class 2606 OID 35758)
-- Name: department address_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT address_fk FOREIGN KEY (address) REFERENCES public.address(id);


--
-- TOC entry 3234 (class 2606 OID 35775)
-- Name: professor address_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT address_fk FOREIGN KEY (address) REFERENCES public.address(id);


--
-- TOC entry 3238 (class 2606 OID 35811)
-- Name: exam course_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT course_fk FOREIGN KEY (course) REFERENCES public.course(id);


--
-- TOC entry 3240 (class 2606 OID 35833)
-- Name: join_course course_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.join_course
    ADD CONSTRAINT course_fk FOREIGN KEY (course) REFERENCES public.course(id);


--
-- TOC entry 3235 (class 2606 OID 35780)
-- Name: professor department_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT department_fk FOREIGN KEY (department) REFERENCES public.department(name);


--
-- TOC entry 3242 (class 2606 OID 35850)
-- Name: join_department department_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.join_department
    ADD CONSTRAINT department_fk FOREIGN KEY (department) REFERENCES public.department(name);


--
-- TOC entry 3236 (class 2606 OID 35785)
-- Name: professor director_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT director_fk FOREIGN KEY (director) REFERENCES public.department(name);


--
-- TOC entry 3233 (class 2606 OID 35763)
-- Name: department faculty_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT faculty_fk FOREIGN KEY (faculty) REFERENCES public.faculty(name);


--
-- TOC entry 3237 (class 2606 OID 35790)
-- Name: professor president_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT president_fk FOREIGN KEY (president) REFERENCES public.faculty(name);


--
-- TOC entry 3241 (class 2606 OID 35828)
-- Name: join_course professor_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.join_course
    ADD CONSTRAINT professor_fk FOREIGN KEY (professor) REFERENCES public.professor(id);


--
-- TOC entry 3239 (class 2606 OID 35816)
-- Name: exam student_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT student_fk FOREIGN KEY (student) REFERENCES public.student(id);


--
-- TOC entry 3243 (class 2606 OID 35845)
-- Name: join_department student_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.join_department
    ADD CONSTRAINT student_fk FOREIGN KEY (student) REFERENCES public.student(id);


-- Completed on 2022-12-10 10:50:41 UTC

--
-- PostgreSQL database dump complete
--

