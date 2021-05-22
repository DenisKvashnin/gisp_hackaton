package com.gisp.controller;

import com.gisp.domain.MeasureSupport;
import com.gisp.service.MeasureSupportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MeasureSupport> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(measureSupportService.findByStringId(id));
    }

    @PostMapping
    public ResponseEntity<List<MeasureSupport>> findById(@RequestBody List<String> measureSupports){
        return ResponseEntity.ok(measureSupportService.findByStringIds(measureSupports));
    }
}
