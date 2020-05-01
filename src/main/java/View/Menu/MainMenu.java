package View.Menu;

import Control.Controller;
import Model.Customer;
import Model.Manager;
import Model.Seller;

public class MainMenu extends Menu {

    public MainMenu() {
        options.add("products");
        options.add("offs");
        options.add("login menu");
        options.add("Account-Based orders");
        options.add("help");
        options.add("back");

    }

    private static void goToNahieKarbari() {
        if (Customer.class.equals(Controller.getLoggedInAccount().getClass())) {
            CustomerMenu.getCustomerMenu().execute();
        } else if (Manager.class.equals(Controller.getLoggedInAccount().getClass())) {
            ManagerMenu.getManagerMenu().execute();
        } else if (Seller.class.equals(Controller.getLoggedInAccount().getClass())) {
            SellerMenu.getSellerMenu().execute();
        } else {
            System.out.println("No user is logged in.");
        }
    }

    public void execute() {
        String input;
        do {
            showCommands();
            System.out.println("Enter Number :");
            if(!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()){
                continue;
            }
            switch (input.trim()) {
                case "1":
//                    productsMenu.execute();
                    break;
                case "2":
                    //off menu
//                    offMenu.execute();
                    break;
                case "3":
                    LoginMenu.getLoginMenu().execute();
                    showCommands();
                    break;
                case "4":
                    goToNahieKarbari();
                case "5":
                    showCommands();
                    break;
                case "6":
                    return;
            }
        }while (!input.equalsIgnoreCase("end"));
    }
}
