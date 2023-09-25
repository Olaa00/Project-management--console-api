package abeczkowska.view.console;

import abeczkowska.model.Document;

import abeczkowska.persistence.Persistable;

import java.util.List;

public interface DocumentView extends ConsoleView{
    void display(List<Persistable> documents);
    void display(Document document);

    String getViewName();
}