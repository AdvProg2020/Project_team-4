package View.Menu;

import View.Manager;

import static View.CommandsSource.findEnum;
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

    public void execute(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "VIEW_CART":

                case "PURCHASE":

                case "VIEW_ORDERS":

                case "VIEW_BALANCE":

                case "VIEW_DISCOUNT_CODES":

                default:
                    super.execute(input);

            }
        }
    }
}
