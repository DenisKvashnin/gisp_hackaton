CREATE SEQUENCE measure_support_seq;
--ID,URL,SMALL_NAME,FULL_NAME,NUMBER_NPA,DATE_NPA,NPA_NAME,DESCRIPTION,PURPOSE,OBJECTIVE,TYPE_MERA,TYPE_FORMAT_SUPPORT,SROK_VOZVRATA,PROCENT_VOZVRATA,GUARANTE_PERIODE,GUARANTEE_COST,APPLIANCE_ID,OKVED2,COMPLEXITY,AMOUNT_OF_SUPPORT,REGULARITY_SELECT,PERIOD,DOGOVOR,GOS_PROGRAM,EVENT,DOP_INFO,IS_NOT_ACTIVE,PRICHINA_NOT_ACT,REQ_ZAYAVITEL,REQUEST_PROJECT,IS_SOFINANCE,DOLYA_ISOFINANCE,BUDGET_PROJECT,POKAZATEL_RESULT,TERRITORIAL_LEVEL,REGION_ID,RESPONS_STRUCTURE,ORG_ID
CREATE TABLE IF NOT EXISTS measure_support
(
    ID character varying,
    URL character varying,
    SMALL_NAME character varying,
    FULL_NAME character varying,
    NUMBER_NPA character varying,
    DATE_NPA character varying,
    NPA_NAME character varying,
    DESCRIPTION character varying,
    PURPOSE character varying,
    OBJECTIVE character varying,
    TYPE_MERA character varying,
    TYPE_FORMAT_SUPPORT character varying,
    SROK_VOZVRATA character varying,
    PROCENT_VOZVRATA character varying,
    GUARANTE_PERIODE character varying,
    GUARANTEE_COST character varying,
    APPLIANCE_ID character varying,
    OKVED2 character varying,
    COMPLEXITY character varying,
    AMOUNT_OF_SUPPORT character varying,
    REGULARITY_SELECT character varying,
    PERIOD character varying,
    DOGOVOR character varying,
    GOS_PROGRAM character varying,
    EVENT character varying,
    DOP_INFO character varying,
    IS_NOT_ACTIVE character varying,
    PRICHINA_NOT_ACT character varying,
    REQ_ZAYAVITEL character varying,
    REQUEST_PROJECT character varying,
    IS_SOFINANCE character varying,
    DOLYA_ISOFINANCE character varying,
    BUDGET_PROJECT character varying,
    POKAZATEL_RESULT character varying,
    TERRITORIAL_LEVEL character varying,
    REGION_ID character varying,
    RESPONS_STRUCTURE character varying,
    ORG_ID character varying
);

ALTER SEQUENCE measure_support_seq OWNED BY measure_support.id;

ALTER TABLE measure_support
    ALTER COLUMN id SET DEFAULT nextval('measure_support_seq'::regclass);
