package View.Menu;

import View.Manager;

import static View.CommandsSource.findEnum;
import static View.Manager.*;
import static View.Menu.SellerMenu.editField;

public class ManagerMenu extends Menu {


    public ManagerMenu() {
        options.add("view personal info");
        options.add("edit [field] to [new]");
        options.add("manage users");
        options.add("manage all products");
        options.add("create discount code");
        options.add("view discount codes");
        options.add("manage requests");
        options.add("manage categories");
        options.add("help");
        options.add("back");
    }

    public void execute(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "edit [field] to [new]":
                    editField(input.split("\\s"));
                case "MANAGE_USERS":

                case "MANAGE_ALL_PRODUCT":

                case "CREATE_DISCOUNT_CODE":

                case "VIEW_DISCOUNT_CODE":

                case "MANAGE_REQUESTS":

                case "MANAGE_CATEGORIES":

                default:
                    super.execute(input);

            }
        }
    }

}

