package com.gisp.controller;

import com.gisp.domain.Okved;
import com.gisp.service.OkvedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/okved")
public class OkvedController {

    private final OkvedService okvedService;

    public OkvedController(OkvedService okvedService) {
        this.okvedService = okvedService;
    }

    @GetMapping
    public ResponseEntity<List<Okved>> findAll() {
        return ResponseEntity.ok(okvedService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Okved> findById(@PathVariable("id") Long id) {
        Optional<Okved> okvedOpt = okvedService.findById(id);
        return okvedOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
