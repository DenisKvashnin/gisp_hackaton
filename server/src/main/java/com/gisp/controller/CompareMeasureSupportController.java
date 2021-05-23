package com.gisp.controller;

import com.gisp.domain.CompareMeasureSupportDTO;
import com.gisp.service.CompareMeasureSupportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compare/measure/support")
public class CompareMeasureSupportController {

    private final CompareMeasureSupportService compareMeasureSupportService;

    public CompareMeasureSupportController(CompareMeasureSupportService compareMeasureSupportService) {
        this.compareMeasureSupportService = compareMeasureSupportService;
    }

    @PostMapping
    public ResponseEntity<CompareMeasureSupportDTO> compare(@RequestBody String id){
        return ResponseEntity.ok(compareMeasureSupportService.compare(id));
    }
}
