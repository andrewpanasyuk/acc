CREATE USER fm_accounting WITH PASSWORD 'foxminded';

CREATE DATABASE fm_accounting_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';

GRANT ALL PRIVILEGES ON DATABASE fm_accounting_db TO fm_accounting;

ALTER DATABASE fm_accounting_db OWNER TO fm_accounting;
