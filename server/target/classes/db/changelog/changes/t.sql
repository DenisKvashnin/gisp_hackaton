SELECT *
FROM measure_support
WHERE CASE
          WHEN UPPER(:measure_type) = 'СУБСИДИЯ' THEN UPPER(amount_of_support) LIKE ('%СУБСИ%')
          WHEN UPPER(:measure_type) = 'КОНСУЛЬТАЦИЯ' THEN UPPER(amount_of_support) LIKE ('%КОНСУЛ%')
          WHEN UPPER(:measure_type) = 'НЕМАТЕРИАЛЬНЫЕ' THEN
                      UPPER(appliance_id) LIKE UPPER(('%Радиоэлектронная промышленность%')) AND
                      (UPPER(small_name) LIKE UPPER('%Поддержка МСП%') OR
                       UPPER(small_name) LIKE UPPER('%Предоставление налоговых и административных преференций%') OR
                       UPPER(small_name) LIKE UPPER(
                               '%/Поддержка малого бизнеса и индивидуальной/Взаимодействие с инвесторами/Режим "одного окна" для инвесторов%') OR
                       UPPER(small_name) LIKE UPPER('%Переводческие услуги%') OR
                       UPPER(small_name) LIKE UPPER('%Проведение маркетинговых%') OR
                       UPPER(small_name) LIKE UPPER('%Содействие в подготовке и экспертизе%') OR
                       UPPER(small_name) LIKE UPPER('%Содействие в стандартизации%') OR
                       UPPER(small_name) LIKE UPPER('%Защита интеллектуальной собственности%') OR
                       UPPER(small_name) LIKE UPPER('%Защита интеллектуальной собственности%') OR
                       UPPER(small_name) LIKE UPPER('%Соглашение о защите и поощрении%') OR
                       UPPER(small_name) LIKE UPPER('%Защита интеллектуальной собственности%') OR
                       UPPER(small_name) LIKE UPPER('%Поиск партнеров%') OR
                       UPPER(small_name) LIKE UPPER('%Организация и проведение публичных%') OR
                       UPPER(small_name) LIKE UPPER('%Размещение на электронных-торговых площадках%') OR
                       UPPER(small_name) LIKE UPPER('%Национальные чемпионы%') OR
                       UPPER(small_name) LIKE UPPER('%Государственные научные гранты%') OR
                       UPPER(small_name) LIKE UPPER('%Центр инжиниринга/Особая экономическая зона%') OR
                       UPPER(small_name) LIKE UPPER('Проведение специальных программ обучения%') OR
                       UPPER(small_name) LIKE UPPER('%Консультационная поддержка%')
                          )
          ELSE
              UPPER(amount_of_support) LIKE ('%Займ%')
          END