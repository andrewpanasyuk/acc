--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;


CREATE USER fm_accounting WITH PASSWORD 'foxminded';

--
-- Name: fm_accounting_db; Type: DATABASE; Schema: -; Owner: fm_accounting
--

CREATE DATABASE fm_accounting_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Ukrainian_Ukraine.1251' LC_CTYPE = 'Ukrainian_Ukraine.1251';

GRANT ALL PRIVILEGES ON DATABASE fm_accounting_db TO fm_accounting;

ALTER DATABASE fm_accounting_db OWNER TO fm_accounting;

\connect fm_accounting_db

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- PostgreSQL database dump complete
--

