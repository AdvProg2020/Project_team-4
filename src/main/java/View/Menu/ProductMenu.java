package View.Menu;

import static View.CommandsSource.findEnum;

public class ProductMenu extends Menu {

    private static String productId;

    public static void giveProductId(String productId) {
        ProductMenu.productId = productId;
    }

    public static void showProduct(String productId){
        giveProductId(productId);

    }
    public void execute(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commands.getAllRegex(), input)) {
                case "DIGEST":
                case "ATTRIBUTES":
                case "COMPARE":
                case "COMMENTS":
                default:
                    super.execute(input);

            }
        }
    }
}

