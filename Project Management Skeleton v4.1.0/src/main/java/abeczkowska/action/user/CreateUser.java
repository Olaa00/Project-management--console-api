package abeczkowska.action.user;

import abeczkowska.action.Action;
import abeczkowska.model.Role;
import abeczkowska.view.console.ProjectView;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.UserRepository;

import java.util.List;

public class CreateUser implements Action {


    private final UserRepository userRepository;

    private ProjectView view;

    public CreateUser(UserRepository userRepository, ProjectView projectView) {
        this.userRepository = userRepository;
        this.view = view;
    }
    @Override
    public String getDisplayName() {
        return "CREATE USER: ";
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
    public void execute() {

    }

    @Override
    public void execute(Persistable persistable) {
    userRepository.create(persistable);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return List.of(Role.ADMINISTRATOR);
    }
}
