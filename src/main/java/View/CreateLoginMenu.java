package View;

import static View.CommandProcessor.processCreateAccount;
import static View.CommandProcessor.processLogin;
import static View.Commands.findEnum;
import static View.Manager.*;

import static View.CommandProcessor.processCreateAccount;
import static View.CommandProcessor.processLogin;

public class CreateLoginMenu extends Menu{

    public CreateLoginMenu(){
        options.add("create account [manager|seller|customer] [username]");
        options.add("login [username]");
        options.add("help");
        options.add("back");
    }

    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch(findEnum(commands.getAllRegex(), input)){
                case "CREATE_ACCOUNT":
                    processCreateAccount(input.split("\\s"));
                    break;
                case "LOGIN":
                    processLogin(input.split("\\s"));
                    break;
                case "HELP":
                    help();
                    break;
                case "BACK":
                    if(previousMenu == null){
                        System.err.println("This is your first menu.");
                    }
                    else{
                        previousMenu.run(this, input);
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

