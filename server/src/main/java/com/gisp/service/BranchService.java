package com.gisp.service;

import com.gisp.domain.Branch;
import com.gisp.repository.BranchRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Cacheable("findAllBranch")
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Cacheable("findByIdBranch")
    public Optional<Branch> findById(Long id) {
        return branchRepository.findById(id);
    }
}
