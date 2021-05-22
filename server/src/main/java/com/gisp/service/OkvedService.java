package com.gisp.service;

import com.gisp.domain.Okved;
import com.gisp.repository.OkvedRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OkvedService {
    private final OkvedRepository okvedRepository;

    public OkvedService(OkvedRepository okvedRepository) {
        this.okvedRepository = okvedRepository;
    }

    @Cacheable("findAllOkved")
    public List<Okved> findAll(){
        return okvedRepository.findAll();
    }

    @Cacheable("findByIdOkved")
    public Optional<Okved> findById(Long id){
        return okvedRepository.findById(id);
    }
}
