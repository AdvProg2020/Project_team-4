package View.Menu;

import Control.Controller;
import View.CommandsSource;

import java.util.regex.Matcher;

import static View.Outputs.*;

public class DefaultMenu extends Menu {
    private static DefaultMenu ourDefaultMenu = new DefaultMenu();

    private DefaultMenu(){

    }

    public static DefaultMenu getInstance(){
        return ourDefaultMenu;
    }

    private void createAccount() {
        String input;
        Matcher matcher;
        while (!(input = scanner.nextLine()).trim().equalsIgnoreCase("break")) {
            System.out.println("please Enter type(customer|seller|manger) username password \n" + "" +
                    "sample : \t customer ali 123\n" +
                    "for back write \"break\"");
            if((matcher = getMatcher(input, CommandsSource.CREATE_ACCOUNT.getRegex())).matches()){
                if(printCreateAccountResult(Controller.getOurController().controllerNewAccount(matcher.group(1), matcher.group(2), matcher.group(3)))){
                    return;
                }
            }
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
}
