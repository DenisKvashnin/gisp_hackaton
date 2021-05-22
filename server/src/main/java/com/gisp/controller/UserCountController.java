package com.gisp.controller;

import com.gisp.domain.UserCount;
import com.gisp.service.UserCountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/count")
public class UserCountController {

    private final UserCountService userCountService;

    public UserCountController(UserCountService userCountService) {
        this.userCountService = userCountService;
    }

    @GetMapping
    public ResponseEntity<List<UserCount>> findAll(){
        return ResponseEntity.ok(userCountService.findAll());
    }

    @GetMapping("/findByInn")
    public ResponseEntity<UserCount> findByInn(@RequestParam("inn") String inn){
        return ResponseEntity.ok(userCountService.findByInn(inn));
    }
}
