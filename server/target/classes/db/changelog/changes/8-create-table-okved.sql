CREATE SEQUENCE okved_seq;

CREATE TABLE IF NOT EXISTS okved
(
    id          bigint PRIMARY KEY,
    branch_id   bigint,
    branch_2_id bigint,
    branch_3_id bigint,
    number      character varying,
    description character varying
);

ALTER SEQUENCE okved_seq OWNED BY okved.id;

ALTER TABLE okved
    ALTER COLUMN id SET DEFAULT nextval('okved_seq'::regclass);