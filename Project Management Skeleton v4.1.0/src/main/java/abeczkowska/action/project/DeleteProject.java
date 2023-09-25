package abeczkowska.action.project;

import abeczkowska.action.Action;
import abeczkowska.model.Role;
import abeczkowska.view.console.ProjectView;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.ProjectRepository;

import java.util.List;

public class DeleteProject implements Action {
    private ProjectRepository projectRepository;
    private ProjectView view;


    public DeleteProject(ProjectRepository projectRepository, ProjectView view) {
        this.projectRepository = projectRepository;
        this.view = view;
    }
    @Override
    public String getDisplayName() {
        return "DELETE PROJECT";
    }
    @Override
    public void execute() {

    }

    @Override
    public void execute(Persistable persistable) {
        projectRepository.delete(persistable);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return List.of(Role.MANAGER, Role.ADMINISTRATOR);
    }

    @Override
    public boolean hasRole(Role role) {
        return getAllowedRoles().contains(role);
    }

    @Override
    public boolean hasRole(List<Role> roles) {
        return getAllowedRoles().stream().anyMatch(roles::contains);
    }
}
