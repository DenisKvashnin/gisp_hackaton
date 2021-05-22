package com.gisp.repository;

import com.gisp.domain.Branch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch, Long> {
    List<Branch> findAll();
}
