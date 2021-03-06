package com.gisp.service;

import com.gisp.domain.CompareMeasureSupportDTO;
import com.gisp.domain.MeasureSupport;
import com.gisp.dto.FilterSumDTO;
import com.gisp.repository.MeasureSupportRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeasureSupportService {

    private final MeasureSupportRepository measureSupportRepository;

    public MeasureSupportService(MeasureSupportRepository measureSupportRepository) {
        this.measureSupportRepository = measureSupportRepository;
    }

    public List<MeasureSupport> findAll() {
        return measureSupportRepository.findAll();
    }

    public Optional<MeasureSupport> findById(Long id) {
        return measureSupportRepository.findById(id);
    }


    public List<MeasureSupport> filter(String startSum,
                                       String endSum,
                                       String measureType,
                                       String srokSubsidy,
                                       String procentSubsidy,
                                       String startPprocentVozvrata,
                                       String endProcentVozvrata,
                                       String isSofinance,
                                       List<String> ids) {
        List<MeasureSupport> measureSupports = new ArrayList<>();

        if ((startSum == null && endSum == null && measureType == null && isSofinance == null)
                || (Objects.equals(startSum, "") && Objects.equals(endSum, "") && measureType.equals(""))) {
            measureSupports = measureSupportRepository.findAll();
        } else if (startSum == null && endSum == null && measureType != null) {
            measureSupports = measureSupportRepository.findByMeasureType(measureType);
        } else if ((Objects.equals(startSum, "") && Objects.equals(endSum, "") && !measureType.equals(""))){
            measureSupports = measureSupportRepository.findByMeasureType(measureType);
        }
        else {
            if (startSum != null && endSum != null) {
                if (Long.parseLong(startSum) > Long.parseLong(endSum)) {
                    return Collections.emptyList();
                }
            }
            if (measureType.equalsIgnoreCase("????????")) {
                List<FilterSumDTO> filterSumDTO = measureSupportRepository.filterBySum(
                        measureType)
                        .stream()
                        .filter(f -> f.getStartSum() != null)
                        .filter(f -> f.getEndSum() != null)
                        .filter(f -> Float.parseFloat((f.getStartSum().equals("")
                                ||
                                f.getStartSum().equals(",,")
                                || f.getStartSum().equals(",")) ? "0.0" :
                                f.getStartSum().replace("," , ".")) >= new Float(startSum))
                        .filter(f -> Float.parseFloat(f.getEndSum().equals("") ? "99999999.0"
                                : f.getEndSum().replace("," , ".")) <= new Float(endSum))
                        .collect(Collectors.toList());

                for (FilterSumDTO f : filterSumDTO) {
                    MeasureSupport measureSupportOpt = measureSupportRepository.findById1(f.getId());
                    measureSupports.add(measureSupportOpt);
                }
            } else if (measureType.equalsIgnoreCase("????????????????")) {
                List<FilterSumDTO> filterSumDTOS = measureSupportRepository
                        .filterSubs()
                        .stream()
                        .filter(f -> f.getStartSum() != null)
                        .filter(f -> f.getEndSum() != null)
                        .filter(f -> Float.parseFloat(f.getEndSum()) <= Float.parseFloat(endSum))
                        .collect(Collectors.toList());
                for (FilterSumDTO f : filterSumDTOS) {
                    MeasureSupport measureSupportOpt = measureSupportRepository.findById1(f.getId());
                    measureSupports.add(measureSupportOpt);
                }
            } else if (measureType.equalsIgnoreCase("????????????????????????")) {
                List<FilterSumDTO> filterSumDTOS = measureSupportRepository.findConsultation()
                        .stream()
                        .filter(f -> f.getStartSum() != null)
                        .filter(f -> f.getEndSum() != null)
                        .filter(f -> Float.parseFloat(f.getEndSum()) <= new Float(endSum))
                        .collect(Collectors.toList());
                for (FilterSumDTO f : filterSumDTOS) {
                    MeasureSupport measureSupportOpt = measureSupportRepository.findById1(f.getId());
                    measureSupports.add(measureSupportOpt);
                }
            }else if(measureType.equalsIgnoreCase("????????????????????????????")){
                measureSupports = measureSupportRepository.findByMeasureType(measureType);
            }
        }

        //set isSofinance
        if (measureSupports != null && isSofinance != null) {
            measureSupports = measureSupports
                    .stream()
                    .filter(m -> m.getIsSofinance().equalsIgnoreCase(isSofinance))
                    .collect(Collectors.toList());
        }

        if (ids != null && !ids.isEmpty()) {
            List<MeasureSupport> filteredByIdSupports = new ArrayList<>();
            for (String id : ids) {
                for (MeasureSupport measureSupport : measureSupports) {
                    if (measureSupport.getId() == Long.parseLong(id)) {
                        filteredByIdSupports.add(measureSupport);
                    }
                }
            }
            if (filteredByIdSupports.size() > 100) {
                return filteredByIdSupports.stream().limit(100).collect(Collectors.toList());
            }
            return filteredByIdSupports;
        }
        if (measureSupports.size() > 100) {
            return measureSupports.stream().limit(100).collect(Collectors.toList());
        }
        return measureSupports;
    }


    public MeasureSupport findByStringId(String id) {
        return measureSupportRepository.findByStringId(id);
    }

    public List<MeasureSupport> findByStringIds(List<String> measureSupports) {
        List<MeasureSupport> result = new ArrayList<>();
        for (String s : measureSupports) {
            var m = findByStringId(s);
            result.add(m);
        }
        return result;
    }

    public CompareMeasureSupportDTO findCompareInfoById(String id) {
       return measureSupportRepository.findCompareInfoById(id);
    }
}
