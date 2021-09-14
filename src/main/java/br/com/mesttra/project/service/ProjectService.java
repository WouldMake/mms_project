package br.com.mesttra.project.service;

import br.com.mesttra.project.data.ProjectRepository;
import br.com.mesttra.project.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> listProjects() {
        return this.projectRepository.findAll();
    }
}
