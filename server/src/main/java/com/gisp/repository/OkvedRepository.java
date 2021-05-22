package com.gisp.repository;

import com.gisp.domain.Okved;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OkvedRepository extends CrudRepository<Okved, Long> {
    List<Okved> findAll();
}
