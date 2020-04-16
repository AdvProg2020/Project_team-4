package View.Menu;

import static View.CommandsSource.findEnum;

public class ProductMenu extends Menu {

    public ProductMenu(){
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
    }

    private static String productId;

    private static void giveProductId(String productId) {
        ProductMenu.productId = productId;
    }

    private static void showProduct(String productId){
        giveProductId(productId);

    }

    private static void digest(){

    }

    private static void attributes(){

    }

    private static void compare(){

    }

    private static void comments(){

    }

    public void execute(String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commands.getAllRegex(), input)) {
                case "DIGEST":
                    digest();
                    break;
                case "ATTRIBUTES":
                    attributes();
                    break;
                case "COMPARE":
                    compare();
                    break;
                case "COMMENTS":
                    comments();
                    break;
                default:
                    super.execute(input);


            }
        }
    }
}

