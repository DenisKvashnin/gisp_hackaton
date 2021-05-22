CREATE SEQUENCE  project_seq;

CREATE TABLE IF NOT EXISTS project(id bigint PRIMARY KEY, name character varying);

ALTER SEQUENCE project_seq OWNED BY project.id;

ALTER TABLE project ALTER COLUMN id SET DEFAULT nextval('project_seq'::regclass);