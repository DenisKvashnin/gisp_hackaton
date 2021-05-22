package com.gisp.controller;

import com.gisp.domain.Region;
import com.gisp.service.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final RegionService service;

    public RegionController(RegionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Region>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> findById(@PathVariable("id") Long id) {
        Optional<Region> regionOpt = service.findById(id);
        return regionOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Region> save(@RequestBody Region region) {
        return ResponseEntity.ok(service.save(region));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Region region) {
        service.delete(region);
        return ResponseEntity.noContent().build();
    }
}
