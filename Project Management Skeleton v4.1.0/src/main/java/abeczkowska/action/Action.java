package abeczkowska.action;

import abeczkowska.model.Role;
import abeczkowska.persistence.Persistable;

import java.util.List;

public interface Action {
    boolean hasRole(Role role);

    boolean hasRole(List<Role> roles);

    String getDisplayName();

    void execute();

    void execute(Persistable persistable);

    List<Role> getAllowedRoles();
}
