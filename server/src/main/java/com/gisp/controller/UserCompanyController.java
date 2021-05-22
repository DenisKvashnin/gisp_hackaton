package com.gisp.controller;

import com.gisp.domain.Company;
import com.gisp.service.UserCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/company")
public class UserCompanyController {

    private final UserCompanyService userCompanyService;

    public UserCompanyController(UserCompanyService userCompanyService) {
        this.userCompanyService = userCompanyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return ResponseEntity.ok(userCompanyService.findAll());
    }

    @PostMapping("/saveByInn")
    public ResponseEntity<Company> save(@RequestBody String inn) {
        Company company = userCompanyService.saveByInn(inn);
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<Company> save(@RequestBody Company company) throws IllegalAccessException {
        return ResponseEntity.ok(userCompanyService.save(company));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Company company){
        userCompanyService.delete(company);
        return ResponseEntity.noContent().build();
    }
}
