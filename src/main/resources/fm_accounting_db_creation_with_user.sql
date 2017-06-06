--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

CREATE USER fm_accounting WITH PASSWORD 'foxminded';

CREATE DATABASE fm_accounting_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';

GRANT ALL PRIVILEGES ON DATABASE fm_accounting_db TO fm_accounting;

ALTER DATABASE fm_accounting_db OWNER TO fm_accounting;



--
-- PostgreSQL database dump complete
--
