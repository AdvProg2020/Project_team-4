package View;

import static View.Manager.*;

public class SellerMenu extends Menu {

    public SellerMenu() {
        options.add("view personal info");
        options.add("view company information");
        options.add("view sales history");
        options.add("manage products");
        options.add("view [productId]");
        options.add("view buyers [productId]");
        options.add("edit [productId]");
        options.add("add product");
        options.add("remove product [productId]");
        options.add("show categories");
        options.add("view offs");
        options.add("view balance");
        options.add("help");
        options.add("back");
    }

    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "view personal info").find()) {
                getPersonalInfo();
            } else if (getMatcher(input, "view company information").find()) {
                getCompanyInfo();
            } else if (getMatcher(input, "view sales history").find()) {
                getSalesHistory();
            } else if (getMatcher(input, "manage products").find()) {
                manageProducts();
            } else if (getMatcher(input, "view [productId]").find()) {
                getProductInfo();
            } else if (getMatcher(input, "view buyers [productId]").find()) {
                getProductBuyers();
            } else if (getMatcher(input, "edit [productId]").find()) {
                showEditableField();
            } else if (getMatcher(input, "add product").find()) {
                getProductInfoForAdd();
            } else if (getMatcher(input, "remove product [productId]").find()) {
                removeProduct();
            } else if (getMatcher(input, "show categories").find()) {
                showCategories();
            } else if (getMatcher(input, "view offs").find()) {
                viewOffs();
            } else if (getMatcher(input, "view balance").find()) {
                viewBalance();
            } else if (getMatcher(input, "help").find()) {
                help();
            } else if (getMatcher(input, "back").find()) {
                if (previousMenu == null) {
                    System.err.println("This your first menu.");
                } else {
                    previousMenu.run(this, input);
                }
            } else {
                if (Manager.isValidCommand(input)) {
                    System.err.println("You must login first");
                } else {
                    System.err.println("invalid command");
                }

            }
        }
    }
}

