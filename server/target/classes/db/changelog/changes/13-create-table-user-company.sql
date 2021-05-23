CREATE SEQUENCE  user_company_seq;

CREATE TABLE IF NOT EXISTS user_company(id bigint PRIMARY KEY, company_inn character varying);

ALTER SEQUENCE user_company_seq OWNED BY user_company.id;

ALTER TABLE user_company ALTER COLUMN id SET DEFAULT nextval('user_company_seq'::regclass);