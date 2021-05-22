CREATE SEQUENCE  hello_seq;

CREATE TABLE IF NOT EXISTS hello(id bigint PRIMARY KEY, name character varying);

ALTER SEQUENCE hello_seq OWNED BY hello.id;

ALTER TABLE hello ALTER COLUMN id SET DEFAULT nextval('hello_seq'::regclass);