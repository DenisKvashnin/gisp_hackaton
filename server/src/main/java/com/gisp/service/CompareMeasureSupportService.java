package com.gisp.service;

import com.gisp.domain.CompareMeasureSupportDTO;
import org.springframework.stereotype.Service;

@Service
public class CompareMeasureSupportService {

    private final MeasureSupportService measureSupportService;

    public CompareMeasureSupportService(MeasureSupportService measureSupportService) {
        this.measureSupportService = measureSupportService;
    }

    public CompareMeasureSupportDTO compare(String id) {
       return measureSupportService.findCompareInfoById(id);
    }
}
