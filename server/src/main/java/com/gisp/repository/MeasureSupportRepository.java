package com.gisp.repository;

import com.gisp.domain.MeasureSupport;
import com.gisp.dto.FilterSumDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeasureSupportRepository extends CrudRepository<MeasureSupport, Long> {
    List<MeasureSupport> findAll();

    @Query("SELECT procent_vozvrata FROM measure_support \n" +
            "WHERE (((:start_sum IS NULL AND :end_sum IS NULL) \n" +
            "          OR UPPER(amount_of_support) LIKE '%СУММА%ЗАЙМА%:start_sum%:end_sum%')\n" +
            "\t\t  OR (:start_sum IS NOT NULL AND :end_sum IS NULL AND '%СУММА%ЗАЙМА%от :start_sum%') \n" +
            "\t\t  OR (:start_sum IS NULL AND :end_sum IS NOT NULL AND '%СУММА%ЗАЙМА%до :end_sum%')) \n" +
            "AND (:measure_type IS NULL OR UPPER(small_name) LIKE UPPER('%:measure_type%'))")
    List<MeasureSupport> filter(@Param("start_sum") String startSum,
                                @Param("end_sum") String endSum,
                                @Param("measure_type") String measureType,
                                @Param("srok_subsidy") String srokSubsidy,
                                @Param("procent_subsidy") String procentSubsidy,
                                @Param("start_procent_vozvrata") String startProcentVozvrata,
                                @Param("end_procent_vozvrata") String endProcentVozvrata);


    @Query("SELECT " +
            "id,\n" +
            "substring(src.summ1, 0, Position(' ' in src.summ1)) start_sum " +
            ",substring(replace(src.summ1, ' ', ''), Position(' ' in src.summ1)) end_sum " +
            "      FROM (SELECT trim(regexp_replace(src1.summ, '[^0-9,]+', ' ', 'g')) summ1, src1.id\n" +
            "            FROM (SELECT (regexp_match(UPPER(amount_of_support), 'СУММА '||UPPER(:measure_type)||'.*МЛН'))[1] summ, ms.id\n" +
            "\t\t\t\t  FROM measure_support ms\n" +
            "                  WHERE is_not_active = 'false' AND UPPER (amount_of_support) LIKE ('%СУММА%' || UPPER(:measure_type) || '%')) src1) src")
    List<FilterSumDTO> filterBySum(@Param("measure_type") String measureType);

    @Query("SELECT * FROM measure_support WHERE id = :id")
    MeasureSupport findById1(@Param("id") String id);

    @Query("SELECT \n" +
            "     src1.id,\n" +
            "     0.0 start_sum\n" +
            "\t ,case\n" +
            "\t\t   WHEN  regexp_match(UPPER(src1.summ), 'МЛН') IS NOT NULL THEN\n" +
            "\t\t    CAST( COALESCE(replace(trim(regexp_replace((regexp_match(UPPER(src1.summ), UPPER('НЕ БОЛЕЕ.*МЛН')))[1], '[^0-9,]+', ' ',\n" +
            "                                           'g')), ',', '.'), '0') AS numeric) + 1\n" +
            "           WHEN regexp_match(UPPER(src1.summ), 'МИЛЛИОН') IS NOT NULL THEN\n" +
            "             CAST( COALESCE( replace(trim(regexp_replace((regexp_match(UPPER(src1.summ), UPPER('НЕ БОЛЕЕ.*МИ')))[1], '[^0-9,]+', ' ',\n" +
            "                                           'g')), ',', '.'), '0') AS numeric)  + 2 \n" +
            "           WHEN regexp_match(UPPER(src1.summ), 'РУБ') IS NOT NULL THEN\n" +
            "            CAST(COALESCE( replace(trim(regexp_replace((regexp_match(UPPER(src1.summ), UPPER('НЕ БОЛЕЕ.*РУ')))[1], '[^0-9,]+', ' ',\n" +
            "                                           'g')), ',', '.'), '0') AS numeric) * 1000 / 1000000\n" +
            "\n" +
            "           WHEN regexp_match(UPPER(src1.summ), 'ТЫС') IS NOT NULL THEN\n" +
            "             CAST( COALESCE( replace(trim(regexp_replace((regexp_match(UPPER(src1.summ), UPPER('НЕ БОЛЕЕ.*ТЫ')))[1], '[^0-9,]+', ' ',\n" +
            "                                           'g')), ',', '.') , '0') AS numeric) / 1000 + 4\n" +
            "           ELSE \n" +
            "\t\t      0\n" +
            "\t\t   END end_sum\n" +
            "       \n" +
            "FROM (\n" +
            "         SELECT (regexp_match(UPPER(amount_of_support),\n" +
            "                              UPPER('НО НЕ БОЛЕЕ.*[МЛН|РУБ|ТЫС]')))[1], \n" +
            "\t   amount_of_support summ, ms.id\n" +
            "         FROM measure_support ms\n" +
            "         WHERE AND is_not_active = 'false' AND UPPER (amount_of_support) LIKE UPPER ('%СУБСИ%') AND (regexp_match(UPPER(amount_of_support),\n" +
            "                              UPPER('НО НЕ БОЛЕЕ.*[МЛН|РУБ|ТЫС|МИЛЛ]')))[1] NOT IN ('10 30 100 1 80 50 1 100 100 5 200') \n" +
            "AND id NOT IN ('8308297', '7763874')) src1\n" +
            "WHERE ROUND(\n" +
            "                  (\n" +
            "                          LENGTH(src1.summ)\n" +
            "                          - LENGTH(REPLACE(src1.summ, '.', ''))\n" +
            "                      ) / LENGTH('.')) < 3\n" +
            "  AND ROUND(\n" +
            "                  (\n" +
            "                          LENGTH(src1.summ)\n" +
            "                          - LENGTH(REPLACE(src1.summ, ',', ''))\n" +
            "                      ) / LENGTH(',')) < 3\n" +
            "\t\t\t\t\t \n" +
            "  AND src1.summ IS NOT NULL\n" +
            "  AND src1.summ <> ''")
    List<FilterSumDTO> filterSubs();

    @Query("SELECT * FROM measure_support ms\n" +
            "            WHERE UPPER (amount_of_support) LIKE UPPER('%Консульт%') OR UPPER(small_name) LIKE UPPER('%Консульт%') " +
            "AND is_not_active = 'false'")
    List<FilterSumDTO> findConsultation();

    @Query("SELECT * FROM measure_support WHERE id = :id")
    MeasureSupport findByStringId(@Param("id") String id);

    @Query("SELECT * FROM measure_support WHERE UPPER (amount_of_support) LIKE \n" +
            "\t  CASE WHEN UPPER(:measure_type) = 'СУБСИДИЯ' THEN ('%СУБСИ%') WHEN UPPER(:measure_type) = 'КОНСУЛЬТАЦИЯ' THEN ('%КОНСУЛ%') ELSE \n" +
            "\t('%ЗАЙМ%') END ")
    List<MeasureSupport> findByMeasureType(@Param("measure_type") String measureType);
}
