package com.gisp.repository;

import com.gisp.domain.MeasureType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasureTypeRepository extends CrudRepository<MeasureType, Long> {
    List<MeasureType> findAll();
}
