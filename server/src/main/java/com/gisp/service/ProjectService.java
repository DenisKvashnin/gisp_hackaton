package com.gisp.service;

import com.gisp.domain.Project;
import com.gisp.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public Project save(Project project) {
        Optional<Project> projectOpt = projectRepository.findById(project.getId());
        if (projectOpt.isPresent()) {
            return projectRepository.save(projectOpt.get());
        }
        project.setId(null);
        return projectRepository.save(project);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }
}
