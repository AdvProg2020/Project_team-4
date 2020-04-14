package View.Menu;

import View.Manager;

import static View.Manager.*;

public class CustomerMenu extends Menu {

    public CustomerMenu() {
        options.add("view personal info");
        options.add("view cart");
        options.add("purchase");
        options.add("view orders");
        options.add("view balance");
        options.add("view discount codes");
        options.add("products");
        options.add("view categories");
        options.add("filtering");
        options.add("sorting");
        options.add("show products");
        options.add("show product [productId]");
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
            } else if (getMatcher(input, "view cart").find()) {

            } else if (getMatcher(input, "purchase").find()) {

            } else if (getMatcher(input, "view orders").find()) {

            } else if (getMatcher(input, "view balance").find()) {

            } else if (getMatcher(input, "view discount codes").find()) {

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
