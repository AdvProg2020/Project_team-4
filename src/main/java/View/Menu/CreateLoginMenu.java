package View.Menu;

import Control.Controller;

import static View.CommandsSource.findEnum;
import static View.Outputs.printCreateAccountResult;
import static View.Outputs.printLoginResult;

public class CreateLoginMenu extends Menu {

    public CreateLoginMenu() {
        options.add("create account [manager|seller|customer] [username]");
        options.add("login [username]");
        options.add("help");
        options.add("back");
    }

    public void createAccount(String type, String username, String password) {
        printCreateAccountResult(Controller.getOurController().requestCreateAccount(type, username, password));
    }

    public void login(String username, String password) {
        printLoginResult(Controller.getOurController().requestLogin(username, password));
    }

    public String requestPassword() {
        System.out.println("Enter your password:");
        return scanner.nextLine();
    }

    public void execute(Menu previousMenu, String input) {

        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commands.getAllRegex(), input)) {
                String[] splitInput = input.split("\\s");
                case "CREATE_ACCOUNT":
                    createAccount(splitInput[2], splitInput[3], requestPassword());
                    break;
                case "LOGIN":
                    login(splitInput[1], requestPassword());
                    break;
                default:
                    Menu.execute(null, input);
            }
        }

    }
}

