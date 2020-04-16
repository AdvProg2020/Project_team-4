package View.Menu;

import Control.Controller;
import View.CommandsSource;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.CommandsSource.findEnum;
import static View.Outputs.*;

public abstract class Menu {
    protected Menu previousMenu;
    public static Scanner scanner;
    protected CommandsSource commands;
    protected ArrayList<String> options = new ArrayList<>();
    private ArrayList<String> validCommands = new ArrayList<>();

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public static String getType() {
        return Controller.getOurController().requestLoginedUser();
    }

    public boolean isAnyUserLogin() {
        return Controller.getOurController().requestIsAnyUserLogin();
    }

    public boolean isValidCommand(String command) {
        return !findEnum(commands.getAllRegex(), command).equals("Very wrong");
    }

    protected void showCommands() {
        for (String option : options) {
            System.out.println("---> " + option);
        }
    }

    private void createAccount(String type, String username, String password) {
        printCreateAccountResult(Controller.getOurController().controllerNewAccount(type, username, password));
    }

    private void login(String username, String password) {
        printLoginResult(Controller.getOurController().controllerLogin(username, password));
    }

    private void logout() {
        printLogoutResult(Controller.getOurController().requestLogout());
    }

    private String requestPassword() {
        System.out.println("Enter your password:");
        return scanner.nextLine();
    }

    public void execute(String input) {
        String[] splitInput = input.split("\\s");
        switch (findEnum(commands.getAllRegex(), input)) {
            case "CREATE_ACCOUNT":
                createAccount(splitInput[2], splitInput[3], requestPassword());
                break;
            case "LOGIN":
                login(splitInput[1], requestPassword());
                break;
            case "LOGOUT":
                logout();
                break;
            case "HELP":
                showCommands();
                break;
            case "BACK":
                if (previousMenu == null) {
                    System.err.println("This your first menu.");
                } else {
                    return;
                }
                break;
            default:
                if (isValidCommand(input)) {
                    System.err.println("You must login first");
                } else {
                    System.err.println("invalid command");
                }
                break;
        }
    }

}
