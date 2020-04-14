package View.Menu;

import View.Manager;

import static View.CommandProcessor.processCreateAccount;
import static View.CommandProcessor.processLogin;
import static View.CommandsSource.findEnum;
import static View.Manager.*;

public class CreateLoginMenu extends Menu {

    public CreateLoginMenu(){
        options.add("create account [manager|seller|customer] [username]");
        options.add("login [username]");
        options.add("help");
        options.add("back");
    }

    public void execute(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch(findEnum(commandsSource.getAllRegex(), input)){
                case "CREATE_ACCOUNT":
                    processCreateAccount(input.split("\\s"));
                    break;
                case "LOGIN":
                    processLogin(input.split("\\s"));
                    break;
                case "HELP":
                    showCommands();
                    break;
                case "BACK":
                    if(previousMenu == null){
                        System.err.println("This is your first menu.");
                    }
                    else{
                        previousMenu.execute(this, input);
                    }
                    break;
                default:
                    if (Manager.isValidCommand(input)) {
                        System.err.println("You must login first");
                    } else {
                        System.err.println("invalid command");
                    }
                    break;
            }
        }

    }
}

