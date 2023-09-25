package abeczkowska;

import abeczkowska.view.ConsoleMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import abeczkowska.action.Action;
import pl.ttpsc.javaupdate.project.action.document.*;
import pl.ttpsc.javaupdate.project.action.project.*;
import abeczkowska.action.user.CreateUser;
import abeczkowska.config.Config;
import abeczkowska.model.User;
import abeczkowska.repository.DocumentRepository;
import abeczkowska.repository.ProjectRepository;
import abeczkowska.repository.ProjectsByUserRepository;
import abeczkowska.repository.UserRepository;
import pl.ttpsc.javaupdate.project.service.LoginREC;
import abeczkowska.view.console.ConsoleView;
import abeczkowska.repository.UserAuthorizationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
public class ConsoleApplication {

    private static final Logger CONSOLE_APP_LOGGER = LoggerFactory.getLogger(ConsoleApplication.class);
    private static ConsoleView currentView;
    private static List<Action> actions;

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        DocumentRepository documentRepository = new DocumentRepository();
        ProjectsByUserRepository projectsByUserRepository = new ProjectsByUserRepository();
        UserAuthorizationRepository userAuthorizationRepository = new UserAuthorizationRepository();

        Config config = new Config()
                .withSqlPersistence()
                .setActions(Arrays.asList(
                        // project
                        new CreateProject(projectRepository, null),
                        new DeleteProject(projectRepository, null),
                        new ListOneProject(projectRepository, null),
                        new ListAllProjects(projectRepository, null),
                        new ShowWholeProjectDetails(projectsByUserRepository, null),

                        // user
                        new CreateUser(userRepository, null),

                        // document
                        new CreateDocument(documentRepository, null),
                        new DeleteDocument(documentRepository, null),
                        new EditDocument(documentRepository, null),
                        new ShowDocumentDetails(documentRepository, null),
                        new ListDocuments(documentRepository, null)
                ));

        ConsoleMenu menu = new ConsoleMenu();

        LoginREC loginREC = ConsoleMenu.getUserCredentials();
       //todo authorize
        Optional<User> currentUser = userAuthorizationRepository.authorize(loginREC);




        if (currentUser.isPresent()) {
            CONSOLE_APP_LOGGER.info("LOGIN SUCCESSFUL! \n YOUR CREDENTIALS: ");
            CONSOLE_APP_LOGGER.info(currentUser.get().toString());

            String option = null;

            do {
                menu.showMenu(config.getActions());
                option = menu.getInput();

                config.getActions().get(Integer.parseInt(option)).execute();
            } while (!option.equals("00"));

        } else {
            CONSOLE_APP_LOGGER.error("UPS! INCORRECT LOGIN OR PASSWORD");
        }
    }
}
