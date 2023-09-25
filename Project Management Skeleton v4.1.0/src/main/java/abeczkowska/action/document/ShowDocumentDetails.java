package abeczkowska.action.document;

import abeczkowska.action.Action;
import abeczkowska.model.Document;
import abeczkowska.model.Role;
import abeczkowska.view.console.DocumentView;
import abeczkowska.persistence.Persistable;

import abeczkowska.repository.DocumentRepository;

import java.util.List;

public class ShowDocumentDetails implements Action {

    private final DocumentRepository documentRepository;

    private final DocumentView view;

    public ShowDocumentDetails(DocumentRepository documentRepository, DocumentView view) {
        this.documentRepository = documentRepository;
        this.view = view;
    }

    @Override
    public String getDisplayName() {
        return "SHOW DOCUMENT DETAILS";
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(Persistable persistable) {

    }

    public void execute(int id) {
        Document document = (Document) documentRepository.findById(id);
        view.display(document);
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
