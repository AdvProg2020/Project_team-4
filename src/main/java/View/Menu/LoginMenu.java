package View.Menu;

import Control.Controller;
import View.CommandsSource;

import java.util.regex.Matcher;

import static View.Outputs.*;

public class LoginMenu extends Menu {

    private static LoginMenu loginMenu = new LoginMenu();

    public static LoginMenu getLoginMenu(){
        return loginMenu;
    }

    private LoginMenu() {
        options.add("create account");
        options.add("login");
        options.add("logout");
        options.add("help");
        options.add("back");
    }

    public void execute() {
        String input;
        do {
            showCommands();
            System.out.println("Enter Number :");
            if(!CommandsSource.isThisRegexMatch("(\\d)", input = scanner.nextLine())){
                continue;
            }
            switch (input.trim()) {
                case "1":
                    createAccount();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    logout();
                    break;
                case "4":
                    showCommands();
                    break;
                case "5":
                    return;
            }
        }while (!input.equalsIgnoreCase("end"));
    }

    private void createAccount() {
        String error = "please Enter type(customer|seller|manger) and username \n" + "" +
                "sample : \t customer ali\n" +
                "for back write \"back\"";
        // create account
        Matcher matcher1 = CommandsSource.getField(error, "(customer|seller|manager) (\\S+)");
        if (matcher1 == null) {
            return;
        }
        Matcher matcher2 = CommandsSource.getField("Enter a valid password", "(\\S+)");
        if (matcher2 == null) {
            return;
        }
        //////har dafe dastooro bayad bezane ya bere too while
        printCreateAccountResult(Controller.getOurController().controllerNewAccount(matcher1.group(1),
                matcher1.group(2),
                matcher2.group(1)));
    }

    private void login() {
        String error = "please Enter username \n" + "" +
                "sample : \t login ali\n" +
                "for back write \"break\"";
        Matcher matcher1 = CommandsSource.getField(error, "login\\s(\\S+)");
        if (matcher1 == null) {
            return;
        }
        Matcher matcher2 = CommandsSource.getField("Enter a valid password", "(\\S+)");
        if (matcher2 == null) {
            return;
        }
        printLoginResult(Controller.getOurController().controllerLogin(matcher1.group(1), matcher2.group(1)));
    }


    private void logout() {
        printLogoutResult(Controller.getOurController().logout());
    }


}
