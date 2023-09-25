package abeczkowska.service;

import abeczkowska.model.Project;
import abeczkowska.model.Role;
import abeczkowska.repository.ProjectRepository;

public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project save(Project project, Role role) {

        return null;
    }
}


