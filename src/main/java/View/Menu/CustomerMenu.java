package View.Menu;

import Control.Controller;
import Model.SaveAndLoad;

import java.util.regex.Matcher;


public class CustomerMenu extends Menu {

    private static final Menu customerMenu = new CustomerMenu();

    public CustomerMenu() {
        options.add("view personal info #");
        options.add("view cart #");
        options.add("purchase #");
        options.add("view orders #");
        options.add("view balance #");
        options.add("view discount codes #");
        options.add("products #");
        options.add("view categories #");
        options.add("filtering #");
        options.add("sorting #");
        options.add("show products #");
        options.add("show product [productId] #");
        options.add("help");
        options.add("back");
        options.add("logout #");
    }

    public static Menu getCustomerMenu() {
        return customerMenu;
    }

//    private static Menu viewAndEditPersonalInfo() {
//        return new Menu() {
//            private void personalInfo() {
//                Matcher matcher = getField("Enter in this format: edit [field]", "edit\\s(firstname|lastname|credit|phonenumber|email|password)");
//                if(matcher == null){
//                    return;
//                }
//                Controller.getOurController().editField(matcher.group(1));
//            }
//            @Override
//            protected void execute() {
//                System.out.println(Controller.getOurController().getLoggedInAccount());
//                String input;
//                do {
//                    System.out.println("Enter 1 for edit a field and 2 for back:");
//                    if(!isThisRegexMatch("(\\d)", input = scanner.nextLine())){
//                        continue;
//                    }
//                    switch (input) {
//                        case "1":
//                            personalInfo();
//                            break;
//                        case "2":
//                            return;
//                    }
//                }while (!input.equalsIgnoreCase("end"));
//            }
//        };
//    }
    private static Menu viewAndEditPersonalInfo() {
        return new Menu() {
            private void personalInfo() {
                Matcher matcher = getField("Enter in this format: edit [field] for back write back\n" +
                        "warning you can't change username!", "edit\\s(firstname|lastname|credit|phonenumber|email|password)");
                if(matcher == null){
                    return;
                }
                Controller.getOurController().editField(matcher.group(1));
                SaveAndLoad.getSaveAndLoad().writeJSON(Controller.getOurController().getLoggedInAccount(), Controller.getOurController().getLoggedInAccount().getClass(), Controller.getOurController().getLoggedInAccount().getUserName());
                System.out.println("Changed well");
            }
            @Override
            protected void execute() {
                System.out.println(Controller.getOurController().getLoggedInAccount());
                String input;
                do {
                    System.out.println("Enter 1 for edit a field and 2 for back:");
                    if(!isThisRegexMatch("(\\d)", input = scanner.nextLine())){
                        continue;
                    }
                    switch (input) {
                        case "1":
                            personalInfo();
                            break;
                        case "2":
                            return;
                    }
                }while (!input.equalsIgnoreCase("end"));
            }
        };
    }

    private static Menu getCartMenu() {
        return new Menu() {
            @Override
            protected void execute() {
                options.add("show products");
                options.add("view [productId]");
                options.add("increase [productId]");
                options.add("decrease [productId]");
                options.add(" show total price");
                options.add(" purchase");
                options.add("end");
                String input = "";
                do {
                    show();
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch (input.trim()) {
                        case "1":
                            System.out.println(Controller.getOurController().showCart());
                            break;
                        case "2":
                            //TODO : voorood be safe mahsool
                            break;
                        case "3":
                            String productIdToIncrease = getField("Enter productId to increase: ", "(\\S+)").group(1);
                            Controller.getOurController().increaseOrDecreaseProductNo(productIdToIncrease, +1);
                            break;
                        case "4":
                            String productIdToDecrease = getField("Enter productId to decrease: ", "(\\S+)").group(1);
                            Controller.getOurController().increaseOrDecreaseProductNo(productIdToDecrease, -1);
                            break;
                        case "5":
                            System.out.println(Controller.getOurController().calculateCartCost());
                            break;
                        case "6":
                            //TODO: purchase
                            break;
                        case "7":
                            return;
                    }
                }while (true);
            }
        };
    }

    

    @Override
    public void execute() {
        String input;
        do {
            show();
            System.out.println("Enter Number :");
            if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                continue;
            }
            switch (input.trim()) {
                case "1":
                    viewAndEditPersonalInfo().execute();
                    break;
                case "2":
                    getCartMenu().execute();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "12":
                    break;
                case "13":
                    show();
                    break;
                case "14":
                    return;
                case "15":
                    LoginMenu.logout();
                    break;
            }
        } while (!input.equalsIgnoreCase("end"));
    }
}
