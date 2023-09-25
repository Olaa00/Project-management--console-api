package abeczkowska.action.document;

import abeczkowska.action.Action;
import abeczkowska.model.Role;
import abeczkowska.view.console.ProjectView;
import abeczkowska.persistence.Persistable;

import abeczkowska.repository.DocumentRepository;

import java.util.List;

public class ListDocuments implements Action {

    private final DocumentRepository documentRepository;
    private final ProjectView view;

    public ListDocuments(DocumentRepository documentRepository, ProjectView view) {
        this.documentRepository = documentRepository;
        this.view = view;
    }

    @Override
    public String getDisplayName() {
        return "SHOW ALL DOCUMENTS";
    }


public void execute() {
    List<? extends Persistable> result = documentRepository.findAll();

    result.stream().forEach(c -> System.out.println(c.toString()));
  //view.display((List<Project>) result);
//    view.display(List< Document > document);
} //todo

    @Override
    public void execute(Persistable persistable) {

    }

    @Override
    public List<Role> getAllowedRoles() {
        return List.of(Role.MANAGER, Role.ENGINEER, Role.HR);
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
