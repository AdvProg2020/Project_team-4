package View.Menu;


public class SellerMenu extends Menu {

    private static Menu sellerMenu = new SellerMenu();

    public SellerMenu() {
        options.add("view company information");
        options.add("view sales history");
        options.add("manage products");
        options.add("add product");
        options.add("remove product [productId]");
        options.add("show categories");
        options.add("view offs");
        options.add("view balance");
        options.add("help");
        options.add("back");
        options.add("logout");
    }

    public static Menu getSellerMenu() {
        return sellerMenu;
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
        }while(!input.equalsIgnoreCase("end"));
    }

}