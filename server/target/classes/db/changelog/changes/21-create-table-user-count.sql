CREATE SEQUENCE user_count_seq;

CREATE TABLE user_count
(
    id            bigint,
    egrul_regdate character varying,
    inn           character varying,
    kpp           character varying,
    ogrn          character varying,
    name_full     character varying,
    name_short    character varying,
    people_count  character varying
);

ALTER SEQUENCE user_count_seq OWNED BY user_count.id;

ALTER TABLE user_count
    ALTER COLUMN id SET DEFAULT nextval('user_count_seq'::regclass);


INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('42 251', '7715906332', '771501001', '1127746139564', '"АКЦИОНЕРНОЕ ОБЩЕСТВО ""КОНЦЕРН ""АВТОМАТИКА"""',
        '"АО ""КОНЦЕРН ""АВТОМАТИКА"""', '0');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('42 194', '5406588223', '540401001', '1155476076040', '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""ВЕКТОР-М"""',
        '"""ВЕКТОР-М"""', '0');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('40 254', '5231005742', '523101001', '1105252000699',
        '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""АПС - СЕРВИС"""', '"ООО ""АПС - СЕРВИС"""', '3');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('41 344', '1650260128', '165001001', '1131650004518',
        '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ТОРГОВО-ПРОИЗВОДСТВЕННАЯ КОМПАНИЯ ""ПРОМЫШЛЕННАЯ БЕЗОПАСНОСТЬ"""',
        '"ООО ТПК ""ПБ"""', '70');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('41 450', '5263099888', '526301001', '1135263003358', '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""ЗУМ"""',
        '"ООО ""ЗУМ"""', '44');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('42 621', '5261107413', '526101001', '1165275044440',
        '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""ЭКОСПЕЦОДЕЖДА"""', '"ООО ""ЭКОСПЕЦОДЕЖДА"""', '0');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('37 530', '5049011877', '504801001', '1025006467210',
        '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""ШАТТДЕКОР"""', '"ООО ""ШАТТДЕКОР"""', '225');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('37 970', '5262122118', '526201001', '1035205786483',
        '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ЧАСТНОЕ ОХРАННОЕ ПРЕДПРИЯТИЕ ""ЭГИОН"""', '"ООО ЧОП ""ЭГИОН"""',
        '1');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('38 957', '5043028803', '504301001', '1065043015311', '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""МЫЛОВАР"""',
        '"ООО ""МЫЛОВАР"""', '1');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('40 658', '7802744800', '780201001', '1117847098929',
        '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""НАУЧНО-ИССЛЕДОВАТЕЛЬСКИЙ ИНСТИТУТ ИННОВАЦИОННЫХ ТЕХНОЛОГИЙ И МАТЕРИАЛОВ"""',
        '"ООО ""ИННОВАЦИОННЫЙ ИССЛЕДОВАТЕЛЬСКИЙ ИНСТИТУТ"""', '5');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('40 107', '1207009668', '121001001', '1071218000380', '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""АНЛЭЙ"""',
        '"ООО ""АНЛЭЙ"""', '0');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('39 344', '2311102023', '231101001', '1072311008813',
        '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""КУБАНЬ-РЕНТГЕН МТ"""', '"ООО ""КУБАНЬ-РЕНТГЕН МТ"""', '15');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('37 636', '6323068663', '632401001', '1036300991781', '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""АЛХИМ"""',
        '"ООО ""АЛХИМ"""', '61');
INSERT INTO user_count(egrul_regdate, inn, kpp, ogrn, name_full, name_short, people_count)
VALUES ('42 901', '5049015303', '505501001', '1065049000554',
        '"ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ ""РОШАЛЬСКИЙ ЗАВОД ПЛАСТИФИКАТОРОВ"""',
        '"ООО ""РОШАЛЬСКИЙ ЗАВОД ПЛАСТИФИКАТОРОВ"""', '0');
