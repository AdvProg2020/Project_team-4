package View.Menu;

import View.Manager;

import static View.CommandProcessor.*;
import static View.CommandProcessor.processDigest;
import static View.CommandsSource.findEnum;
import static View.Manager.commandsSource;

public class ProductMenu extends Menu {

    private static String productId;

    public ProductMenu() {

    }

    public static void giveProductId(String productId) {
        ProductMenu.productId = productId;
    }


    public void execute(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commandsSource.getAllRegex(), input)) {
                case "DIGEST":
                    processDigest(productId);
                    switch(findEnum(commandsSource.getAllRegex(), input)){
                        case "ADD_TO_CART":
                            processAddToCart();
                            break;
                        case "SELECT_SELLER":
                            processSelectSeller(input.split("\\s"));
                            break;
                    }
                    break;
                case "ATTRIBUTES":

                case "COMPARE":

                case "COMMENTS":

                case "HELP":

                case "BACK":
                    if (previousMenu == null) {
                        System.err.println("This your first menu.");
                    } else {
                        previousMenu.execute(this, input);
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

