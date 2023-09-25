package abeczkowska.action.project;

import abeczkowska.action.Action;
import abeczkowska.model.Role;
import abeczkowska.view.console.ProjectView;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.ProjectRepository;

import java.util.List;

public class ListAllProjects implements Action {
    private final ProjectRepository projectRepository;
    private final ProjectView view;

    public ListAllProjects(ProjectRepository projectRepository, ProjectView view) {
        this.projectRepository = projectRepository;
        this.view = view;
    }

    @Override
    public String getDisplayName() {
        return "SHOW ALL PROJECTS";
    }


    @Override
    public void execute() {
//        List<? extends Persistable> result = projectRepository.findAll();
//        view.display((List<Project>) result);
        List<? extends Persistable> result = projectRepository.findAll();
        result.forEach(c -> System.out.println(c.toString()));
    }

    @Override
    public void execute(Persistable persistable) {

    }

    @Override
    public List<Role> getAllowedRoles() {
        return List.of(Role.ADMINISTRATOR);
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