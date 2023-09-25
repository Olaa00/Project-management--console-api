package abeczkowska.action.document;

import abeczkowska.action.Action;
import abeczkowska.model.Document;
import abeczkowska.model.Role;
import abeczkowska.model.User;
import abeczkowska.view.console.ProjectView;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.DocumentRepository;

import java.util.List;

public class EditDocument implements Action {

    private final DocumentRepository documentRepository;

    private final ProjectView view;

    public EditDocument(DocumentRepository documentRepository, ProjectView view) {
        this.documentRepository = documentRepository;
        this.view = view;
    }

    @Override
    public String getDisplayName() {
        return "EDIT DOCUMENT";
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(Persistable persistable) {

    }

    public void execute(Persistable persistable, int id) {
        documentRepository.update(persistable, id);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return null;
    }

    @Override
    public boolean hasRole(Role role) {
        return false;
    }

    @Override
    public boolean hasRole(List<Role> roles) {
        return false;
    }

    public boolean canUpdateDocument(int documentId, User user) {
        Document document = (Document) documentRepository.findById(documentId);

        return document.getCreatorId() == user.getId();
    }
}
