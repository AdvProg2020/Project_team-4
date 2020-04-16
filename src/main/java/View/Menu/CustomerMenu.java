package View.Menu;

import Control.Controller;
import Model.Customer;
import View.CommandsSource;
//import View.Manager;

import java.util.ArrayList;

import static View.CommandsSource.findEnum;
//import static View.Manager.*;

public class CustomerMenu extends Menu {

    private Menu nextMenu = null;

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

    public static Menu getLFinalPurchaseMenu() {
        return new Menu() {
            @Override
            protected void showCommands() {
                super.showCommands();
            }

            @Override
            public void execute(String input) {
                super.execute(input);
            }
        };
    }

    private static Menu getCartMenu() {
        return new Menu() {
            @Override
            protected void showCommands() {

            }

            @Override
            public void execute(String input) {
                Controller.getOurController().showCart();
                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
                    String[] splitInput = input.split("\\s");
                    switch (findEnum(commands.getAllRegex(), input)) {
                        case "SHOW_PRODUCTS" :
                            Controller.getOurController().showProducts();
                            break;
                        case "View" :
                            Controller.getOurController().showProduct();
                            break;
                            //case ""
                    }
                }
            }
        };
    }

    private static Menu getViewOrdersMenu() {
        return new Menu() {
            @Override
            protected void showCommands() {

            }

            @Override
            public void execute(String input) {
                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
                    String[] splitInput = input.split("\\s");
                    switch (findEnum(commands.getAllRegex(), input)) {
                        case "SHOW_ORDER" :
                            Controller.getOurController().showOrder(splitInput[2]);
                            break;
                        case "RATE_PRODUCT" :
                            Controller.getOurController().rateProduct();
                    }
                }
            }
        };
    }

    private static void receiveInformation() {
        String input = "";
        System.out.println("Enter address:\nphoneNumber:");
        String address = scanner.nextLine().trim();
        String phoneNumber = CommandsSource.getField("Please enter a valid barcode", "(\\d+)");
        Controller.getOurController().purchase(address, phoneNumber);
    }

    private static void offCodeCheck() {
        String input = "";
        System.out.println("Enter your offCode");
        String offCode = CommandsSource.getField("Please enter a valid barcode", "(\\S+)");
        Controller.getOurController().checkOffCodeAndSet(offCode);
    }

    private static void pay() {
        Controller.getOurController().pay();
    }

    public void execute(String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "VIEW_CART":
                    nextMenu = getCartMenu();
                    nextMenu.showCommands();
                    nextMenu.execute("");
                case "PURCHASE":
                    receiveInformation();
                    offCodeCheck();
                    pay();
                case "VIEW_ORDERS":
                    nextMenu = getViewOrdersMenu();
                    nextMenu.showCommands();
                    nextMenu.execute("");
                case "VIEW_BALANCE":
                    Controller.getOurController().showCustomerBalance();
                case "VIEW_DISCOUNT_CODES":
                    Controller.getOurController().showCustomerDiscountCodes();
                default:
                    super.execute(input);

            }
        }
    }
}
