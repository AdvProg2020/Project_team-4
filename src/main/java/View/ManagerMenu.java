package View;

import static View.CommandProcessor.getMatcher;

public class ManagerMenu extends Menu {


    public ManagerMenu() {
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
    }

    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = CommandProcessor.scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "view personal info").find()) {
                CommandProcessor.getPersonalInfo();
            } else if (getMatcher(input, "edit [field] to [new]").find()) {
                CommandProcessor.editField(input.split("\\s"));
            } else if (getMatcher(input, "manage users").find()) {

            } else if (getMatcher(input, "manage all products").find()) {

            } else if (getMatcher(input, "create discount code").find()) {

            } else if (getMatcher(input, "view discount codes").find()) {

            } else if (getMatcher(input, "manage requests").find()) {

            } else if (getMatcher(input, "manage categories").find()) {

            } else if (getMatcher(input, "help").find()) {
                help();
            } else if (getMatcher(input, "back").find()) {
                if (previousMenu == null) {
                    System.err.println("This your first menu.");
                } else {
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

