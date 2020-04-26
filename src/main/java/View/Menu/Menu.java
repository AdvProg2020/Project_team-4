package View.Menu;

import Control.Controller;
import View.CommandsSource;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.Outputs.*;

public abstract class Menu {

    public Menu() {
        options.add("create account");
        options.add("login");
        options.add("logout");
        options.add("back");
    }

    private void createAccount() {
        String input;
        Matcher matcher;
        String error = "please Enter type(customer|seller|manger) username \n" + "" +
                "sample : \t create account customer ali\n" +
                "for back write \"break\"";
        Matcher matcher1 = CommandsSource.getField(error, "create\\saccount\\s(customer|seller|manger)\\s(\\S+)");
        if (matcher1 == null) {
            return;
        }
        Matcher matcher2 = CommandsSource.getField("Enter a valid password", "(\\S+)");
        if (matcher1 == null) {
            return;
        }
        //////har dafe dastooro bayad bezane ya bere too while
        printCreateAccountResult(Controller.getOurController().controllerNewAccount(matcher1.group(1), matcher1.group(2), matcher2.group(1)));
    }

    private void login() {
        String error = "please Enter username \n" + "" +
                "sample : \t login account customer ali\n" +
                "for back write \"break\"";
        Matcher matcher1 = CommandsSource.getField(error, "login\\s(\\S+)");
        if (matcher1 == null) {
            return;
        }
        Matcher matcher2 = CommandsSource.getField("Enter a valid password", "(\\S+)");
        if (matcher1 == null) {
            return;
        }
        printLoginResult(Controller.getOurController().controllerLogin(matcher1.group(1), matcher2.group(1)));
    }


    private void logout() {
        printLogoutResult(Controller.getOurController().logout());
    }


    public void execute() {
        System.out.println("Enter Number :");
        String input;
        do {
            showCommands();
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
                case "4" :
                    return;
            }
        }while (!input.equalsIgnoreCase("end"));
    }


    protected static Scanner scanner = new Scanner(System.in);
    protected CommandsSource commands;
    protected ArrayList<String> options = new ArrayList<>();


    public static Scanner getScanner() {
        return scanner;
    }

    protected void showCommands() {
        int number = 0;
        for (String option : options) {
            System.out.println(++number + "" + option);
        }
    }

}
