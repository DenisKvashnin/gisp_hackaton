package com.gisp.controller;

import com.gisp.domain.MeasureSupport;
import com.gisp.dto.FilterSupportDTO;
import com.gisp.service.SupportOfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support/offer")
public class SupportOfferController {

    private final SupportOfferService supportOfferService;

    public SupportOfferController(SupportOfferService supportOfferService) {
        this.supportOfferService = supportOfferService;
    }

    @PostMapping("/filter")
    public ResponseEntity<List<MeasureSupport>> filter(@RequestBody FilterSupportDTO filterSupportDTO){
        return ResponseEntity.ok(supportOfferService.filter(filterSupportDTO));
    }
}
