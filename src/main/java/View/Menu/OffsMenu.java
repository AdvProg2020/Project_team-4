package View.Menu;

import Control.Controller;
import View.CommandsSource;

import static View.Outputs.*;

public class OffsMenu extends Menu {

    private ProductMenu productMenu = new ProductMenu();

    public OffsMenu() {
        options.add("show product [productId]");
        options.add("help");
        options.add("back");
        options.add("logout");
    }

    public static void offsList() {
        printOffsListResult(Controller.getOurController().requestOffsList());
    }

    private void showProduct(String[] splitInput, String input) {
        productMenu.setProductId(splitInput[2]);
        productMenu.execute(input);
    }

    public void execute(String input) {
        offsList();
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            if ("SHOW_PRODUCT".equals(CommandsSource.findEnum(commands.getAllRegex(), input))) {
                showProduct(splitInput, input);
            } else {
                super.execute(input);
            }
        }
    }
}
