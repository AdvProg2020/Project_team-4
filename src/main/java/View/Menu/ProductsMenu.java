package View.Menu;

import View.CommandProcessor;
import View.Manager;

import static View.Manager.getMatcher;

public class ProductsMenu extends Menu {
    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "view category").find()) {
                CommandProcessor.processCreateAccount(input.split("\\s"));
            } else if (getMatcher(input, "filtering").find()) {
                CommandProcessor.processLogin(input.split("\\s"));
            } else if (getMatcher(input, "sorting").find()) {

            } else if (getMatcher(input, "show products").find()) {

            } else if (getMatcher(input, "show product [productId]").find()) {

            } else if (getMatcher(input, "help").find()) {
                showCommands();
            } else if (getMatcher(input, "back").find()) {
                if (previousMenu == null) {
                    System.err.println("This your first menu.");
                } else {
                    previousMenu.run(this, input);
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

