package abeczkowska.action.document;

import abeczkowska.action.Action;
import abeczkowska.model.Role;
import abeczkowska.view.console.ProjectView;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.DocumentRepository;

import java.util.List;

public class DeleteDocument implements Action {

    private final DocumentRepository documentRepository;
    private final ProjectView view;

    public DeleteDocument(DocumentRepository documentRepository, ProjectView view) {
        this.documentRepository = documentRepository;
        this.view = view;
    }

    @Override
    public String getDisplayName() {
        return "DELETE DOCUMENT";
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(Persistable persistable) {
        documentRepository.delete(persistable);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return List.of(Role.MANAGER);
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
