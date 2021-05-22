package com.gisp.controller;

import com.gisp.domain.Company;
import com.gisp.service.CompanyService;
import com.gisp.service.UserCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService,
                             UserCompanyService userCompanyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @PostMapping
    public ResponseEntity<Company> save(@RequestBody Company company) {
        Company newCompany = companyService.save(company);
        return ResponseEntity.ok(newCompany);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Company company) {
        companyService.delete(company);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByInn")
    public ResponseEntity<Company> findByInn(@RequestParam String inn){
        return ResponseEntity.ok(companyService.findByInn(inn));
    }
}
