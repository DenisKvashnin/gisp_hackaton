package com.gisp.service;

import com.gisp.domain.Hello;
import com.gisp.repository.HelloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelloService {

    private final HelloRepository helloRepository;

    public HelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    public List<Hello> findAll() {
        return helloRepository.findAll();
    }

    public Hello save(Hello hello) {
        Optional<Hello> helloOpt = helloRepository.findById(hello.getId());
        if (helloOpt.isEmpty()) {
            hello.setId(null);
            return helloRepository.save(hello);
        }
        return helloRepository.save(hello);
    }
}
