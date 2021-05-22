SELECT case
           WHEN regexp_match(UPPER(src1.summ), 'МИЛЛИОН') IS NOT NULL THEN
               replace(trim(regexp_replace((regexp_match(UPPER(src1.summ), UPPER('НЕ БОЛЕЕ.*МИ')))[1], '[^0-9,]+', ' ',
                                           'g')), ',', '.')
           WHEN regexp_match(UPPER(src1.summ), 'РУБ') IS NOT NULL THEN
               replace(trim(regexp_replace((regexp_match(UPPER(src1.summ), UPPER('НЕ БОЛЕЕ.*РУ')))[1], '[^0-9,]+', ' ',
                                           'g')), ',', '.')

           WHEN regexp_match(UPPER(src1.summ), 'ТЫС') IS NOT NULL THEN
               replace(trim(regexp_replace((regexp_match(UPPER(src1.summ), UPPER('НЕ БОЛЕЕ.*ТЫ')))[1], '[^0-9,]+', ' ',
                                           'g')), ',', '.')
           END,
       src1.id

FROM (
         SELECT (regexp_match(UPPER(amount_of_support),
                              UPPER('НО НЕ БОЛЕЕ.*[МЛН|РУБ|ТЫС]')))[1],
	   amount_of_support summ, ms.id
         FROM measure_support ms
         WHERE UPPER (amount_of_support) LIKE UPPER ('%СУБСИ%')) src1
WHERE ROUND(
                  (
                          LENGTH(src1.summ)
                          - LENGTH(REPLACE(src1.summ, '.', ''))
                      ) / LENGTH('.')) < 3
  AND ROUND(
                  (
                          LENGTH(src1.summ)
                          - LENGTH(REPLACE(src1.summ, ',', ''))
                      ) / LENGTH(',')) < 3
  AND src1.summ IS NOT NULL
  AND src1.summ <> ''