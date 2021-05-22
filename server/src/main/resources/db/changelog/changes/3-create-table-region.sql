CREATE SEQUENCE region_seq;

CREATE TABLE region
(
    id bigint PRIMARY KEY,
    name character varying,
    type character varying,
    name_with_type character varying,
    federal_district character varying,
    kladr_id character varying,
    fias_id character varying,
    okato character varying,
    oktmo character varying,
    tax_office character varying,
    postal_code character varying,
    iso_code character varying,
    timezone character varying,
    geoname_code character varying,
    geoname_id character varying,
    geoname_name character varying
);

ALTER SEQUENCE region_seq OWNED BY region.id;

ALTER TABLE region
    ALTER COLUMN id SET DEFAULT nextval('region_seq'::regclass);