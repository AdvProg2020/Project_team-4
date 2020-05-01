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
    }

    public static Menu getCustomerMenu() {
        return customerMenu;
    }

    public void execute() {
        String input = "";
        System.out.println("Enter your command :");
        do {
            showCommands();
            System.out.println("Enter Number :");
            if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                continue;
            }
            switch (input.trim()) {
                case "1":
                    break;
            }
        } while (!input.equalsIgnoreCase("end"));
    }
}
