package abeczkowska.action.project;


import abeczkowska.action.Action;
import abeczkowska.model.Project;
import abeczkowska.model.Role;
import abeczkowska.view.console.ProjectView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.ProjectRepository;

import java.util.Arrays;
import java.util.List;

public class ListOneProject implements Action {
	private static final Logger logger = LoggerFactory.getLogger(ListOneProject.class.getName());

	private final ProjectView view;
	private final ProjectRepository repository;

	public ListOneProject(ProjectRepository projectRepository, ProjectView view) {
		this.repository = projectRepository;
		this.view = view;
	}

	@Override
	public String getDisplayName() {
		return "SHOW PROJECT";
	}


	@Override
	public void execute() {
		String projectName = view.getProjectName();
		List<Project> projects = repository.findByName(projectName);
		projects.stream().forEach(c -> System.out.println(c.toString()));
		view.display((Project) projects);
	}

	@Override
	public void execute(Persistable persistable) {

	}

	@Override
	public List<Role> getAllowedRoles() {
		return Arrays.asList(Role.ADMINISTRATOR, Role.HR, Role.ENGINEER, Role.MANAGER);
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

