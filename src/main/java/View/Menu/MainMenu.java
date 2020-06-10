package View.Menu;

import Control.Controller;
import Model.Customer;
import Model.Manager;
import Model.Seller;

public class MainMenu extends Menu {

    public MainMenu() {
        options.add("products #");
        options.add("offs #");
        options.add("login menu");
        options.add("Account-Based orders #");
        options.add("help");
        options.add("back");

    }

    private static void goToUserSection() {
        if(Controller.getOurController().getLoggedInAccount() == null){
            System.out.println("you should login first");
            return;
        }
        if (Customer.class.equals(Controller.getOurController().getLoggedInAccount().getClass())) {
            CustomerMenu.getCustomerMenu().execute();
        } else if (Manager.class.equals(Controller.getOurController().getLoggedInAccount().getClass())) {
            ManagerMenu.managerMenu().execute();
        } else if (Seller.class.equals(Controller.getOurController().getLoggedInAccount().getClass())) {
            SellerMenu.getSellerMenu().execute();
        }
    }

    public void execute() {
        String input;
        do {
            show();
            System.out.println("Enter Number :");
            if(!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()){
                continue;
            }
            switch (input.trim()) {
                case "1":
                    ProductsMenu.getProductsMenu().execute();
                    break;
                case "2":
                    OffsMenu.getOffsMenu().execute();
                    break;
                case "3":
                    LoginMenu.getLoginMenu().execute();
                    break;
                case "4":
                    goToUserSection();
                    break;
                case "5":
                    show();
                    break;
                case "6":
                    return;
            }
        }while (!input.equalsIgnoreCase("end"));
    }
}
