package com.gisp.controller;

import com.gisp.domain.Project;
import com.gisp.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable("id") Long id) {
        Optional<Project> projectOpt = projectService.findById(id);
        if (projectOpt.isPresent()) {
            return ResponseEntity.ok(projectOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Project> save(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
    }


    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Project project) {
        projectService.delete(project);
        return ResponseEntity.noContent().build();
    }
}
