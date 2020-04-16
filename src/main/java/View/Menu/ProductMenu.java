package View.Menu;

import Control.Controller;
import Model.Seller;

import java.util.ArrayList;

import static View.CommandsSource.findEnum;
import static View.Outputs.printAddToCartResult;

public class ProductMenu extends Menu {

    private String productId;

    public ProductMenu() {
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
        options.add("");
    }

    private void addToCart(){
        printAddToCartResult(Controller.getOurController().requestAddProductToCart(this.getProductId()));
    }

    private void selectSeller(){
        ArrayList<Seller> sellers = new ArrayList<>();
        sellers.addAll(Controller.getOurController().requestProductSeller(this.getProductId()));
        for (Seller seller : sellers) {
            System.out.println(seller);
        }
        System.out.println("Enter the name of seller to select.");
        scanner.nextLine();
    }

    private static Menu digestMenu() {
        return new Menu() {
            @Override
            protected void showCommands() {
                options.add("add to cart");
                options.add("select seller [seller_username]");
                options.add("help");
                options.add("back");
                for (String option : options) {
                    System.out.println("---> " + option);
                }
            }

            @Override
            public void execute(String input) {
                System.out.println("Enter your command :");
                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
                    switch (findEnum(commands.getAllRegex(), input)) {
                        case "ADD_TO_CART":
                            addToCart();
                            break;
                        case "SELECT_SELLER":
                            selectSeller();
                            break;
                        default:
                            super.execute(input);
                    }
                }
            }
        }
    }

    private static void digest() {

    }

    private static void attributes() {

    }

    private static void compare() {

    }

    private static void comments() {

    }

    public void execute(String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commands.getAllRegex(), input)) {
                case "DIGEST":
                    digestMenu().execute(input);
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}

