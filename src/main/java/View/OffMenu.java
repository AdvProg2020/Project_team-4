package View;

import static View.CommandProcessor.*;
import static View.Manager.*;

public class OffMenu extends Menu {
    public OffMenu() {
        options.add("show product [productId]");
        options.add("help");
        options.add("back");
        options.add("logout");
    }

    public void run(Menu previousMenu, String input) {
        processOffsList(input.split("\\s"));
        System.out.println("Enter your command :");
        while (!(input = CommandProcessor.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch(commands.findEnum(commands.getAllRegex(), input)){
                case "SHOW_PRODUCT":
                    processShowProduct(input.split("\\s"));
                case "HELP":
                    help();
                case "BACK":
                    if (previousMenu == null) {
                        System.err.println("This your first menu.");
                    } else {
                        previousMenu.run(this, input);
                    }
                    break;
                case "LOGOUT":
            }
        }
    }
}
