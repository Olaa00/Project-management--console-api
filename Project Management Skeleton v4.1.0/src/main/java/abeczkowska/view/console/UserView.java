package abeczkowska.view.console;

import abeczkowska.model.User;
import abeczkowska.persistence.Persistable;

import java.util.List;

public interface UserView extends ConsoleView {

    void display(List<Persistable> users);
    void display(User user);
    String getViewName();

}
