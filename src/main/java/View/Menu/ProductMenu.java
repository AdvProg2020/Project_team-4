package View.Menu;

import View.Manager;

<<<<<<< HEAD
import static View.CommandProcessor.*;
import static View.CommandProcessor.processDigest;
import static View.CommandsSource.findEnum;
import static View.Manager.commandsSource;
=======
import static View.Commands.findEnum;
import static View.Manager.commands;
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb

public class ProductMenu extends Menu {

    private static String productId;

    public ProductMenu() {

    }

    public static void giveProductId(String productId) {
        ProductMenu.productId = productId;
    }


<<<<<<< HEAD
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

=======
    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commands.getAllRegex(), input)) {
                case "DIGEST":
                case "ATTRIBUTES":
                case "COMPARE":
                case "COMMENTS":
                case "HELP":
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
                case "BACK":
                    if (previousMenu == null) {
                        System.err.println("This your first menu.");
                    } else {
<<<<<<< HEAD
                        previousMenu.execute(this, input);
                    }
                    break;
                case "LOGIN":

                case "CREATE_ACCOUNT":

                case "LOGOUT":

=======
                        previousMenu.run(this, input);
                    }
                    break;
                case "LOGIN":
                case "CREATE_ACCOUNT":
                case "LOGOUT":
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
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

