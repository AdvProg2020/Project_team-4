package View;

import static View.CommandProcessor.getMatcher;

public class CustomerMenu extends Menu {

    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = CommandProcessor.scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "view personal info").find()) {
                CommandProcessor.getPersonalInfo();
            } else if (getMatcher(input, "view cart").find()) {

            } else if (getMatcher(input, "purchase").find()) {

            } else if (getMatcher(input, "view orders").find()) {

            } else if (getMatcher(input, "view balance").find()) {

            } else if (getMatcher(input, "view discount codes").find()) {

            } else if (getMatcher(input, "products").find()) {

            } else if (getMatcher(input, "view categories").find()) {

            } else if (getMatcher(input, "filtering").find()) {

            } else if (getMatcher(input, "sorting").find()) {

            } else if (getMatcher(input, "show products").find()) {

            } else if (getMatcher(input, "show product [productId]").find()) {

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
