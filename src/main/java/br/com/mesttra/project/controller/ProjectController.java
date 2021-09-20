package br.com.mesttra.project.controller;

import br.com.mesttra.project.exception.BusinessException;
import br.com.mesttra.project.model.Project;
import br.com.mesttra.project.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Add project",
            notes = "This method adds a new project.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Project added"),
            @ApiResponse(code = 400, message = "Business exception: Secretariat not found, Secretariat is under investigation, There are not budgets for projects on this folder, There is no budget for projects on this folder"),
            @ApiResponse(code = 500, message = "Internal Error"),
    })
    public Project addProject(@Valid @RequestBody Project project) throws BusinessException { return this.projectService.addProject(project); }

    @GetMapping
    @ApiOperation(value = "List projects",
            notes = "This method lists all projects.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Projects listed"),
            @ApiResponse(code = 500, message = "Internal Error"),
    })
    public List<Project> listProjects() { return projectService.listProjects(); }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find project by id",
            notes = "This method finds a project by its id and returns project data.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Project found"),
            @ApiResponse(code = 500, message = "Internal Error"),
    })
    public Optional<Project> findProject(@PathVariable Long id) { return this.projectService.findProject(id); }
}
