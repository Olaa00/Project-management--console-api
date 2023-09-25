package abeczkowska.view;

import pl.ttpsc.javaupdate.project.service.LoginREC;
import abeczkowska.action.Action;
import abeczkowska.model.Role;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private static Scanner scanner;

    public ConsoleMenu() {
        scanner = new Scanner(System.in);
    }

    public static LoginREC getUserCredentials() {
        System.out.println("ENTER YOUR USERNAME: ");
        String login = scanner.next();

        System.out.println("ENTER YOUR PASSWORD: ");
        String password = scanner.next();

        return new LoginREC(login, password);
    }
    // menu dostępnych akcji dla użytkownika
    public void showMenu(List<? extends Action> actions) {
        System.out.println("MENU: (CLICK '00' TO EXIT APPLICATION)");
        for(int i = 0; i < actions.size(); i++) {
            System.out.printf("%d : %s%n", i, actions.get(i).getDisplayName());
        }

    }
    //menu dostępnych akcji dla użytkownika z ograniczeniem względem roli
    public void showMenuRestrictedByRole(List<? extends Action> actions, Role role) {
        actions = actions.stream().filter(action -> action.hasRole(role)).toList(); // // Filtruje akcje, aby wyświetlać tylko te, które są dostępne dla danej roli

        System.out.println("MENU: (CLICK '00' TO EXIT APPLICATION)");
        for(int i = 0; i < actions.size(); i++) {
            System.out.printf("%d : %s%n", i, actions.get(i).getDisplayName()); //numer i nazwa akcji zgodnie z  pozycją w liście
        }
    }

    public String getInput() {
        return scanner.next();
    }

}
