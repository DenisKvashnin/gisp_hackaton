CREATE SEQUENCE branch_seq;

CREATE TABLE IF NOT EXISTS branch
(
    id   bigint PRIMARY KEY,
    name character varying
);

ALTER SEQUENCE branch_seq OWNED BY branch.id;

ALTER TABLE branch
    ALTER COLUMN id SET DEFAULT nextval('branch_seq'::regclass);