package abeczkowska.action.project;

import abeczkowska.action.Action;
import abeczkowska.model.Role;
import abeczkowska.view.console.ProjectView;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.ProjectsByUserRepository;

import java.util.List;

public class ShowWholeProjectDetails implements Action {


    private final ProjectsByUserRepository projectsByUserRepository;

    public ShowWholeProjectDetails( ProjectsByUserRepository projectsByUserRepository, ProjectView view) {
        this.projectsByUserRepository = projectsByUserRepository;

    }
    @Override
    public String getDisplayName() {
        return "SHOW PROJECT DETAILS";
    }


    @Override
    public void execute() {

    }

    @Override
    public void execute(Persistable persistable) {

    }

    public void execute(int id) {
        projectsByUserRepository.getProjectDetails(id);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return List.of(Role.ADMINISTRATOR, Role.MANAGER);
    }

    @Override
    public boolean hasRole(Role role) {
        return getAllowedRoles().contains(role);
    }

    @Override
    public boolean hasRole(List<Role> roles) {
        return getAllowedRoles().stream().anyMatch(roles::contains);
    } //czy co najmniej jedna z ról z listy roles znajduje się na liście dozwolonych ról
}
