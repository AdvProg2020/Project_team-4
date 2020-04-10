package View;

import static View.CommandProcessor.getMatcher;

public class SellerMenu extends Menu {

    public SellerMenu() {

    }

    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = CommandProcessor.scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "view personal info").find()) {
                CommandProcessor.getPersonalInfo();
            } else if (getMatcher(input, "view company information").find()) {
                CommandProcessor.getCompanyInfo();
            } else if (getMatcher(input, "view sales history").find()) {
                CommandProcessor.getSalesHistory();
            } else if (getMatcher(input, "manage products").find()) {
                CommandProcessor.manageProducts();
            } else if (getMatcher(input, "view [productId]").find()) {
                CommandProcessor.getProductInfo();
            } else if (getMatcher(input, "view buyers [productId]").find()) {
                CommandProcessor.getProductBuyers();
            } else if (getMatcher(input, "edit [productId]").find()) {
                CommandProcessor.showEditableField();
            } else if (getMatcher(input, "add product").find()) {
                CommandProcessor.addProduct();
            } else if (getMatcher(input, "remove product [productId]").find()) {
                CommandProcessor.removeProduct();
            } else if (getMatcher(input, "show categories").find()) {
                CommandProcessor.showCategories();
            } else if (getMatcher(input, "view offs").find()) {
                CommandProcessor.viewOffs();
            } else if (getMatcher(input, "view balance").find()) {
                CommandProcessor.viewBalance();
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

