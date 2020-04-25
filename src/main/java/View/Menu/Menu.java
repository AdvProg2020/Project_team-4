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

    private void createAccount() {
        String input;
        Matcher matcher;
        String error = "please Enter type(customer|seller|manger) username \n" + "" +
                "sample : \t create account customer ali\n" +
                "for back write \"break\"";
        Matcher matcher1 = CommandsSource.getField(error, "create\\saccount\\s(customer|seller|manger)\\s(\\S+)");
        Matcher matcher2 = CommandsSource.getField("Enter a valid password", "(\\S+)");
        if(printCreateAccountResult(Controller.getOurController().controllerNewAccount(matcher1.group(1), matcher1.group(2), matcher2.group(1)))){
            return;
        }
    }

    private void login() {
        String input;
        Matcher matcher;
        while (!(input = scanner.nextLine()).trim().equalsIgnoreCase("break")) {
            System.out.println("please Enter type username password \n" + "" +
                    "sample : \tali 123\n" +
                    "for back write \"break\"");
            if((matcher = getMatcher(input, CommandsSource.LOGIN.getRegex())).matches()){
                if(printLoginResult(Controller.getOurController().controllerLogin(matcher.group(1), matcher.group(2)))){
                    return;
                }
            }
        }
    }


    private void logout() {
        printLogoutResult(Controller.getOurController().logout());
    }


    public void execute(int input) {
        switch (input) {
            case 1:
                createAccount();
                break;
            case 2:
                login();
                break;
            case 3:
                logout();
                break;
        }
    }

    @Override
    protected void execute() {

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
