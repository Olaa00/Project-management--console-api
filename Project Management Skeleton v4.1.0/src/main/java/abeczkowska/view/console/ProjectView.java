package abeczkowska.view.console;

import abeczkowska.model.Project;
import abeczkowska.persistence.Persistable;

import java.util.List;

public interface ProjectView extends ConsoleView{
    void display(List<Persistable> project);
    void display(Project project);

    String getProjectName();
}
