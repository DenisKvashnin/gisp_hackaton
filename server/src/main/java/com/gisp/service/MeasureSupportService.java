package com.gisp.service;

import com.gisp.domain.MeasureSupport;
import com.gisp.dto.FilterSumDTO;
import com.gisp.repository.MeasureSupportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                                       String endProcentVozvrata) {
        List<MeasureSupport> measureSupports = new ArrayList<>();
        if (measureType.equalsIgnoreCase("ЗАЙМ")) {
            List<FilterSumDTO> filterSumDTO = measureSupportRepository.filterBySum(
                    measureType);
            List<FilterSumDTO> filteredBySum = filterSumDTO
                    .stream()
                    .filter(f -> f.getStartSum() != null)
                    .filter(f -> f.getEndSum() != null)
                    .filter(f -> Float.parseFloat((f.getStartSum().equals("")
                            ||
                            f.getStartSum().equals(",,")
                            || f.getStartSum().equals(",")) ? "0.0" : f.getStartSum().replace("," , ".")) >= new Float(startSum))
                    .filter(f -> Float.parseFloat(f.getEndSum().equals("") ? "99999999.0" : f.getEndSum().replace("," , ".")) <= new Float(endSum))
                    .collect(Collectors.toList());

            for (FilterSumDTO f : filteredBySum) {
                MeasureSupport measureSupportOpt = measureSupportRepository.findById1(f.getId());
                measureSupports.add(measureSupportOpt);
            }
        } else if (measureType.equalsIgnoreCase("СУБСИДИЯ")) {
            List<FilterSumDTO> filterSumDTOS = measureSupportRepository
                    .filterSubs();
            filterSumDTOS
                    .stream()
                    .filter(f -> f.getStartSum() != null)
                    .filter(f -> f.getEndSum() != null)
                    .filter(f -> Float.parseFloat(f.getEndSum()) <= new Float(endSum))
                    .collect(Collectors.toList());
            for (FilterSumDTO f : filterSumDTOS) {
                MeasureSupport measureSupportOpt = measureSupportRepository.findById1(f.getId());
                measureSupports.add(measureSupportOpt);
            }
        } else if (measureType.equalsIgnoreCase("КОНСУЛЬТАЦИЯ")) {
            List<FilterSumDTO> filterSumDTOS = measureSupportRepository.findConsultation();
            filterSumDTOS
                    .stream()
                    .filter(f -> f.getStartSum() != null)
                    .filter(f -> f.getEndSum() != null)
                    .filter(f -> Float.parseFloat(f.getEndSum()) <= new Float(endSum))
                    .collect(Collectors.toList());
            for (FilterSumDTO f : filterSumDTOS) {
                MeasureSupport measureSupportOpt = measureSupportRepository.findById1(f.getId());
                measureSupports.add(measureSupportOpt);
            }
        }

        return measureSupports;
    }
}
