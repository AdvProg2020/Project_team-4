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

    private static Menu viewAndEditPersonalInfo() {
        return new Menu() {
            private void personalInfo() {
                Matcher matcher = getField("Enter in this format: edit [field]", "edit\\s(\\S+)");
                switch (Controller.getOurController().editField(matcher.group(1))) {
                    case 1:
                        SaveAndLoad.getSaveAndLoad().writeJSONAccount(Controller.getOurController().getLoggedInAccount(), Controller.getOurController().getLoggedInAccount().getClass().toString());
                        System.out.println("Changed well");
                        break;
                    case 2:
                        System.out.println("Sth went wrong in changing");

                }
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
