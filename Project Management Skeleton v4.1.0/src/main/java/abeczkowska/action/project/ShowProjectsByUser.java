package abeczkowska.action.project;

import abeczkowska.action.Action;
import abeczkowska.model.Project;
import abeczkowska.model.Role;
import abeczkowska.view.console.ProjectView;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.ProjectsByUserRepository;


import java.util.List;

public class ShowProjectsByUser implements Action {

    private final ProjectsByUserRepository projectsByUserRepository;

    private final ProjectView view;

    public ShowProjectsByUser(ProjectsByUserRepository projectsByUserRepository, ProjectView view) {
        this.projectsByUserRepository = projectsByUserRepository;
        this.view = view;
    }

    @Override
    public String getDisplayName() {
        return "SHOW ALL PROJECTS BY USER";
    }

    @Override
    public void execute() {
    }

    @Override
    public void execute(Persistable persistable) {

    }

    public void execute(int id) {
        List<Project> projects = projectsByUserRepository.getAllUserProjects(id);
        view.display((Project) projects);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return List.of(Role.ADMINISTRATOR, Role.MANAGER, Role.ENGINEER, Role.HR);
    }

    @Override
    public boolean hasRole(Role role) {
        return getAllowedRoles().contains(role);
    }

    @Override
    public boolean hasRole(List<Role> roles) {
        return false;
    }
}
