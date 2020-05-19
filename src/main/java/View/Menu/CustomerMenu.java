package View.Menu;

import Control.Controller;
import Model.SaveAndLoad;
import View.Outputs;

import java.util.regex.Matcher;


public class CustomerMenu extends Menu {

    private static final Menu customerMenu = new CustomerMenu();

    public CustomerMenu() {
        options.add("view personal info #");
        options.add("view cart #");
        options.add("view orders #");
        options.add("view balance #");
        options.add("view discount codes #");
        options.add("help");
        options.add("back");
    }

    public static Menu getCustomerMenu() {
        return customerMenu;
    }

    public static Menu viewAndEditPersonalInfo() {
        return new Menu() {
            private void personalInfo() {
                Matcher matcher = getField("Enter in this format: edit [field] for back write back\n" +
                        "warning you can't change username!", "edit\\s(firstname|lastname|credit|phonenumber|email|password)");
                if(matcher == null){
                    return;
                }
                Controller.getOurController().editField(matcher.group(1));
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
                            if(Controller.getOurController().showCart().isEmpty()){
                                System.out.println("no product added to cart!");
                            }else {
                                System.out.println(Controller.getOurController().showCart());
                            }
                            break;
                        case "2":
                            String productId = getField("enter productId", "(\\S+)").group(1);
                            ProductMenu productMenu = new ProductMenu();
                            productMenu.execute(productId);
                            break;
                        case "3":
                            String productIdToIncrease = getField("Enter productId to increase: ", "(\\S+)").group(1);
                            Outputs.printIncreaseOrDecreaseResult(Controller.getOurController().increaseOrDecreaseProductNo(productIdToIncrease, 1));
                            break;
                        case "4":
                            String productIdToDecrease = getField("Enter productId to decrease: ", "(\\S+)").group(1);
                            Controller.getOurController().increaseOrDecreaseProductNo(productIdToDecrease, -1);
                            break;
                        case "5":
                            System.out.println(Controller.getOurController().calculateCartCost());
                            break;
                        case "6":
                            purchase();
                            break;
                        case "7":
                            return;
                    }
                }while (true);
            }
        };
    }

    private static void purchase() {
        Matcher info = getField("Enter info like this: firstName, lastName, phoneNumber, email", "(\\S+),\\s(\\S+),\\s(\\S+),\\s(\\S+)");
        if(info == null){
            return;
        }
        Controller.getOurController().setCustomersField(info.group(1), info.group(2), info.group(3), info.group(4));
        Matcher matcher= getField("Enter your address for deliver the products: ()", "(.+)");
        if(matcher == null){
            return;
        }
        String address = matcher.group(1);
        Controller.getOurController().setCustomerAddress(address);
        matcher = getField("If you have offCode type it here:", "(\\S+)");
        if(matcher == null){
            return;
        }
        String offCode = matcher.group(1);
        Outputs.printPayResult(Controller.getOurController().pay(offCode));
    }

    private static Menu getViewOrdersMenu() {
        return new Menu() {
            @Override
            protected void execute() {
                options.add("show order [orderId]");
                options.add("rate [productId] [1-5]");
                options.add("end");
                show();
                String input = "";
                do {
                    show();
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch (input.trim()) {
                        case "1":
                            String orderId = getField("Enter orderId", "(\\S+)").group(1);
                            System.out.println(Controller.getOurController().showOrderInCustomerMenu(orderId));
                            break;
                        case "2":
                            Matcher productIdAndRate = getField("Enter: [productId] [1-5]", "(\\S+)\\s([1, 5])");
                            String productId = productIdAndRate.group(1);
                            int rate = Integer.parseInt(productIdAndRate.group(2));
                            Controller.getOurController().rateProduct(productId, rate);
                            break;
                        case "3":
                            return;

                    }
                } while (true);
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
                    getViewOrdersMenu().execute();
                    break;
                case "4":
                    System.out.println(Controller.getOurController().getCredit());
                    break;
                case "5":
                    System.out.println(Controller.getOurController().getCustomerDiscountCodes());
                    break;
                case "6":
                    show();
                    break;
                case "7":
                    return;
            }
        } while (!input.equalsIgnoreCase("end"));
    }
}
