package View;

import static View.CommandProcessor.getMatcher;

public class CreateLoginMenu extends Menu {

    public CreateLoginMenu(){
         options.add("create account [manager|seller|customer] [username]");
         options.add("login [username]");
         options.add("help");
         options.add("back");
    }
    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = CommandProcessor.scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "create account [manager|seller|customer] [username]").find()) {
                CommandProcessor.processCreateAccount(input.split("\\s"));
            } else if (getMatcher(input, "login [username]").find()) {
                CommandProcessor.processLogin(input.split("\\s"));
            } else if (getMatcher(input, "help").find()) {
                 help();
            } else if (getMatcher(input, "back").find()) {
                 if(previousMenu == null){
                      System.err.println("This your first menu.");
                 }
                 else{
                      previousMenu.run(this, input);
                 }
            } else {
                 if (CommandProcessor.isValidCommand(input)) {
                      System.err.println("You must login first");
                 } else {
                      System.err.println("invalid command");
                 }

            }
        }

    }
}

