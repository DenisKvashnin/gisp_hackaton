package com.gisp.service;

import com.gisp.domain.MeasureType;
import com.gisp.repository.MeasureTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasureTypeService {

    private final MeasureTypeRepository measureTypeRepository;

    public MeasureTypeService(MeasureTypeRepository measureTypeRepository) {
        this.measureTypeRepository = measureTypeRepository;
    }

    public List<MeasureType> findAll() {
        return measureTypeRepository.findAll();
    }

    public Optional<MeasureType> findById(Long id){
        return measureTypeRepository.findById(id);
    }
}
