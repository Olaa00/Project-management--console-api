package abeczkowska.action.document;

import abeczkowska.action.Action;
import abeczkowska.model.Role;
import org.w3c.dom.views.DocumentView;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.DocumentRepository;


import java.util.List;

public class CreateDocument implements Action {

    private final DocumentView view;
    private final DocumentRepository documentRepository;

    public CreateDocument(DocumentRepository documentRepository, DocumentView view) {
        this.documentRepository = documentRepository;
        this.view = view;
    }

    @Override
    public String getDisplayName() {
        return "CREATE DOCUMENT";
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(Persistable persistable) {
        documentRepository.create(persistable);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return List.of(Role.MANAGER, Role.ENGINEER);
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
