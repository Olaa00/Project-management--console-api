package abeczkowska.view.console;

import abeczkowska.action.Action;
import abeczkowska.persistence.Persistable;

import java.util.List;

public interface ConsoleView {
    void display(List<Persistable> persistables);
    void display(Persistable persistable);
    String getViewName();
    List<? extends Action> getAllowedActions();
}
