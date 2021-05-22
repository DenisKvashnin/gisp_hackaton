package com.gisp.service;

import com.gisp.domain.Region;
import com.gisp.repository.RegionRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository repository;

    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }

    @Cacheable("findAllRegion")
    public List<Region> findAll() {
        return repository.findAll();
    }

    @Cacheable("findByIdRegion")
    public Optional<Region> findById(Long id) {
        return repository.findById(id);
    }

    public Region save(Region region) {
        Optional<Region> regionOpt = repository.findById(region.getId());

        if (regionOpt.isPresent()) {
            return repository.save(region);
        }
        region.setId(null);
        return repository.save(region);
    }

    public void delete(Region region) {
        repository.delete(region);
    }
}
