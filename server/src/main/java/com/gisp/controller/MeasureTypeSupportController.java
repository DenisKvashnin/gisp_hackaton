package com.gisp.controller;

import com.gisp.domain.MeasureType;
import com.gisp.service.MeasureTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measure/type")
public class MeasureTypeSupportController {

    private final MeasureTypeService measureTypeService;

    public MeasureTypeSupportController(MeasureTypeService measureTypeService) {
        this.measureTypeService = measureTypeService;
    }

    @GetMapping
    public ResponseEntity<List<MeasureType>> findAll() {
        return ResponseEntity.ok(measureTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasureType> findById(@PathVariable("id") Long id) {
        Optional<MeasureType> measureTypeOpt = measureTypeService.findById(id);
        return measureTypeOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
