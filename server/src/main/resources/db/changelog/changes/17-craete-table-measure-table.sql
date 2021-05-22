CREATE SEQUENCE measure_type_seq;

CREATE TABLE IF NOT EXISTS measure_type
(
    id bigint PRIMARY KEY,
    description character varying
);

ALTER SEQUENCE measure_type_seq OWNED BY measure_type.id;

ALTER TABLE measure_type
    ALTER COLUMN id SET DEFAULT nextval('measure_type_seq'::regclass);

INSERT INTO measure_support(id, description)
VALUES (1, 'Субсидия');
INSERT INTO measure_support(id, description)
VALUES (2, 'Заим');
INSERT INTO measure_support(id, description)
VALUES (3, 'Консультация');