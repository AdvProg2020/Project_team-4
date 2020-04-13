package View.Menu;

import View.Manager;

import static View.Commands.findEnum;
import static View.Manager.commands;

public class ProductMenu extends Menu {

    private static String productId;

    public ProductMenu() {

    }

    public static void giveProductId(String productId) {
        ProductMenu.productId = productId;
    }


    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commands.getAllRegex(), input)) {
                case "DIGEST":
                case "ATTRIBUTES":
                case "COMPARE":
                case "COMMENTS":
                case "HELP":
                case "BACK":
                    if (previousMenu == null) {
                        System.err.println("This your first menu.");
                    } else {
                        previousMenu.run(this, input);
                    }
                    break;
                case "LOGIN":
                case "CREATE_ACCOUNT":
                case "LOGOUT":
                default:
                    if (Manager.isValidCommand(input)) {
                        System.err.println("You must login first");
                    } else {
                        System.err.println("invalid command");
                    }

            }
        }
    }
}

