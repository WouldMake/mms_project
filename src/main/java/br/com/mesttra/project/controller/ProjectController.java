package br.com.mesttra.project.controller;

import br.com.mesttra.project.exception.BusinessException;
import br.com.mesttra.project.model.Project;
import br.com.mesttra.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@Valid @RequestBody Project project) throws BusinessException { return this.projectService.addProject(project); }

    @GetMapping
    public List<Project> listProjects() { return projectService.listProjects(); }

    @GetMapping("/{id}")
    public Optional<Project> findProject(@PathVariable Long id) { return this.projectService.findProject(id); }
}
