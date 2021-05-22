package com.gisp.controller;

import com.gisp.domain.MeasureSupport;
import com.gisp.service.MeasureSupportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measuresupport")
public class MeasureSupportController {

    private final MeasureSupportService measureSupportService;


    public MeasureSupportController(MeasureSupportService measureSupportService) {
        this.measureSupportService = measureSupportService;
    }

    @GetMapping
    public ResponseEntity<List<MeasureSupport>> findAll(){
        return ResponseEntity.ok(measureSupportService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasureSupport> findById(@PathVariable("id") Long id){
        Optional<MeasureSupport> measureSupportOpt = measureSupportService.findById(id);
        return measureSupportOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
