package com.gisp.controller;

import com.gisp.domain.Hello;
import com.gisp.service.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloSerivce;

    public HelloController(HelloService helloSerivce) {
        this.helloSerivce = helloSerivce;
    }

    @GetMapping
    public ResponseEntity<List<Hello>> findAll() {
        return ResponseEntity.ok(helloSerivce.findAll());
    }

    @PostMapping
    public ResponseEntity<Hello> save(@RequestBody Hello hello) {
        return ResponseEntity.ok(helloSerivce.save(hello));
    }
}
