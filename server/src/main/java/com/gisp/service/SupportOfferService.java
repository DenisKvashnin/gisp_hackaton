package com.gisp.service;

import com.gisp.domain.MeasureSupport;
import com.gisp.dto.FilterSupportDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportOfferService {

    private final MeasureSupportService measureSupportService;

    public SupportOfferService(MeasureSupportService measureSupportService) {
        this.measureSupportService = measureSupportService;
    }

    @Cacheable("filterSupport")
    public List<MeasureSupport> filter(FilterSupportDTO filterSupportDTO) {
        return measureSupportService.filter(
                filterSupportDTO.getStartSum(),
                filterSupportDTO.getEndSum(),
                filterSupportDTO.getMeasureType(),
                filterSupportDTO.getSrokSubsidy(),
                filterSupportDTO.getProcentSubsidy(),
                filterSupportDTO.getStartPprocentVozvrata(),
                filterSupportDTO.getEndProcentVozvrata(),
                filterSupportDTO.getIsSofinance()
        );
    }
}
