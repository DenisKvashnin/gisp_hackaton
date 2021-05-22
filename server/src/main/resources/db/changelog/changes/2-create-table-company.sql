CREATE SEQUENCE company_seq;

CREATE TABLE company
(
    id   bigint PRIMARY KEY,
    name character varying
);

ALTER SEQUENCE company_seq OWNED BY company.id;

ALTER TABLE company
    ALTER COLUMN id SET DEFAULT nextval('company_seq'::regclass);