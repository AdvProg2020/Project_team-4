package View.Menu;

import Control.Controller;
import Model.Seller;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static View.CommandsSource.findEnum;
import static View.Outputs.printAddToCartResult;

public class ProductMenu extends Menu {

    private String productId;

    public ProductMenu() {
        options.add("digestMenu");
        options.add("attributes");
        options.add("compare");
        options.add("comments");
        options.add("back");
        options.add("");
        options.add("");
    }

    private static void addToCart() {
        printAddToCartResult(Controller.getOurController().requestAddProductToCart(this.getProductId()));
    }

    private static void selectSeller() {
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
                options.add("back");
                for (String option : options) {
                    System.out.println("---> " + option);
                }
            }

            @Override
            public void execute() {
                System.out.println("Enter your command :");
                do {
                    showCommands();
                    String input;
                    if (!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()) {
                        continue;
                    }
                    switch (input) {
                        case "1":
                            addToCart();
                            break;
                        case "2":
                            selectSeller();
                            break;
                        case "3":
                            return;
                        default:
                            DefaultMenu.getInstance().execute(Integer.parseInt(input) - 2);
                    }
                } while (true);
            }
        };
    }

    private static void digest() {
        System.out.println("in digest");
    }

    private static void attributes() {
    }

    private static void compare() {
    }

    private static void comments() {
    }

    public void execute() {
        System.out.println("Enter Number :");
        String input;
        while (true) {
            if (!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()) {
                continue;
            }
            switch (findEnum(commands.getAllRegex(), input)) {
                case "1":
                    digestMenu().execute();
                    break;
                case "2":
                    attributes();
                    break;
                case "3":
                    compare();
                    break;
                case "4":
                    comments();
                    break;
                case "5":
                    return;
                default:
                    DefaultMenu.getInstance().execute(Integer.parseInt(input) - options.size() + 3);
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

