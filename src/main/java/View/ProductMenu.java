package View;

import static View.CommandProcessor.getMatcher;

public class ProductMenu extends Menu {

    public ProductMenu() {

    }

    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = CommandProcessor.scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "digest").find()) {

            } else if (getMatcher(input, "attributes").find()) {

            } else if (getMatcher(input, "compare [productID]").find()) {

            } else if (getMatcher(input, "Comments").find()) {

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

