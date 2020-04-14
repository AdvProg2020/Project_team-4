package View.Menu;

import View.Manager;

import static View.CommandProcessor.processCreateAccount;
import static View.CommandProcessor.processLogin;
<<<<<<< HEAD
import static View.CommandsSource.findEnum;
=======
import static View.Commands.findEnum;
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
import static View.Manager.*;

public class CreateLoginMenu extends Menu {

    public CreateLoginMenu(){
        options.add("create account [manager|seller|customer] [username]");
        options.add("login [username]");
        options.add("help");
        options.add("back");
    }

<<<<<<< HEAD
    public void execute(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch(findEnum(commandsSource.getAllRegex(), input)){
=======
    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch(findEnum(commands.getAllRegex(), input)){
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
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
<<<<<<< HEAD
                        previousMenu.execute(this, input);
=======
                        previousMenu.run(this, input);
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
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

