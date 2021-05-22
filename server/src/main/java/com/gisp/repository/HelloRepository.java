package com.gisp.repository;

import com.gisp.domain.Hello;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HelloRepository extends CrudRepository<Hello, Long> {
    List<Hello> findAll();
}
