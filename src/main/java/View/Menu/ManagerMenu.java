package View.Menu;

import View.Manager;

import static View.Manager.*;

public class ManagerMenu extends Menu {


    public ManagerMenu() {
        options.add("view personal info");
        options.add("edit [field] to [new]");
        options.add("manage users");
        options.add("manage all products");
        options.add("create discount code");
        options.add("view discount codes");
        options.add("manage requests");
        options.add("manage categories");
        options.add("help");
        options.add("back");
    }

<<<<<<< HEAD
    public void execute(Menu previousMenu, String input) {
=======
    public void run(Menu previousMenu, String input) {
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "view personal info").find()) {
                getPersonalInfo();
            } else if (getMatcher(input, "edit [field] to [new]").find()) {
                editField(input.split("\\s"));
            } else if (getMatcher(input, "manage users").find()) {

            } else if (getMatcher(input, "manage all products").find()) {

            } else if (getMatcher(input, "create discount code").find()) {

            } else if (getMatcher(input, "view discount codes").find()) {

            } else if (getMatcher(input, "manage requests").find()) {

            } else if (getMatcher(input, "manage categories").find()) {

            } else if (getMatcher(input, "help").find()) {
                showCommands();
            } else if (getMatcher(input, "back").find()) {
                if (previousMenu == null) {
                    System.err.println("This your first menu.");
                } else {
<<<<<<< HEAD
                    previousMenu.execute(this, input);
=======
                    previousMenu.run(this, input);
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
                }
            } else {
                if (Manager.isValidCommand(input)) {
                    System.err.println("You must login first");
                } else {
                    System.err.println("invalid command");
                }

            }
        }
    }

}

