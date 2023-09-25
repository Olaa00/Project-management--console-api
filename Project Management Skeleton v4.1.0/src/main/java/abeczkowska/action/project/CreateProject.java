package abeczkowska.action.project;

import abeczkowska.action.Action;
import abeczkowska.model.Project;
import abeczkowska.model.Role;
import abeczkowska.view.console.ProjectView;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.ProjectRepository;

import java.util.Arrays;
import java.util.List;

public class CreateProject implements Action {

    private final ProjectRepository projectRepository;

    private ProjectView view;

    public CreateProject(ProjectRepository projectRepository,ProjectView view) {
        this.projectRepository = new ProjectRepository();
        this.view = view;
    }

    @Override
    public String getDisplayName() {
        return "CREATE PROJECT";
    }

    @Override
    public void execute(Persistable persistable) {
        System.out.println("CREATED PROJECTt");
    }

    public void execute(Persistable persistable, Role role) {

        Project createdProject = (Project) projectRepository.createProject(persistable, role);
        System.out.println("CREATED PROJECT");
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean hasRole(Role role) {
        return getAllowedRoles().contains(role);
    }

    @Override
    public boolean hasRole(List<Role> roles) {
        return getAllowedRoles().stream().anyMatch(roles::contains);
    }


    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR);
    }
}
