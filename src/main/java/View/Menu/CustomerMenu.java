package View.Menu;

public class CustomerMenu extends Menu {

    private static Menu customerMenu = new CustomerMenu();

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
        options.add("logout");
    }

    public static Menu getCustomerMenu() {
        return customerMenu;
    }

    public void execute() {
        String input = "";
        System.out.println("Enter your command :");
        do {
            show();
            System.out.println("Enter Number :");
            if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                continue;
            }
            switch (input.trim()) {
                case "1":
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
