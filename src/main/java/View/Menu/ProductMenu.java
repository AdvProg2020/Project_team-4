package View.Menu;

import Control.Controller;
import Model.Product;
import Model.Seller;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static View.Outputs.*;

public class ProductMenu extends Menu {

    private String productId;

    public ProductMenu() {
        options.add("digestMenu");
        options.add("attributes");
        options.add("compare");
        options.add("comments");
        options.add("help");
        options.add("back");
    }

    private void addToCart() {
        printAddToCartResult(Controller.getOurController().requestAddProductToCart(this.getProductId()));
    }

    private boolean selectSeller() {
        ArrayList<String> sellers = new ArrayList<>();
        sellers.addAll(Controller.getOurController().requestProductSeller(this.getProductId()));
        for (String seller : sellers) {
            System.out.println(seller);
        }
        Matcher matcher= getField("Enter the name of seller to select.", "(\\S+)");
        if(matcher == null){
            return false;
        }
        String sellerName = matcher.group(1);
        Controller.getOurController().setNameOfSellerOfProductAddedToCart(sellerName);
        return true;
    }

    private Menu digestMenu() {
        return new Menu() {

            @Override
            public void execute() {
                options.add("add to cart");
                options.add("select seller [seller_username]");
                options.add("back");
                boolean sellerSelected = false;
                do {
                    System.out.println("Enter your choice:");
                    show();
                    String input;
                    if (!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()) {
                        continue;
                    }
                    switch (input.trim()) {
                        case "1":
                            if(sellerSelected){
                                System.out.println("please sellect seller first!");
                                continue;
                            }
                            if(Controller.getOurController().getLoggedInAccount() == null){
                                System.out.println("please login first!");
                                LoginMenu.getLoginMenu().execute();
                                continue;
                            }
                            addToCart();
                            break;
                        case "2":
                            if(Controller.getOurController().getLoggedInAccount() == null){
                                System.out.println("please login first!");
                                LoginMenu.getLoginMenu().execute();
                                continue;
                            }
                            sellerSelected = selectSeller();
                            break;
                        case "3":
                            return;
                    }
                } while (true);
            }
        };
    }

    private void attributes() {
        printAttributeResult(Product.getProductWithBarcode(productId));
    }

    private void compare() {
        System.out.println("Please enter id of product that you want to compare to this:");
        String productIdComparison = getScanner().nextLine();
        printAttributeResult(Product.getProductWithBarcode(productIdComparison));
        System.out.println("-----------------------------------------------");
        printAttributeResult(Product.getProductWithBarcode(productId));
    }

    private static void addNewComment(){
        System.out.println("Enter your comment or enter \"back\" to back to previous menu");
        String input;
        while(!(input = getScanner().nextLine()).equalsIgnoreCase("back")){
            printNewCommentResult(Controller.getOurController().newComment(input));
        }
    }

    private Menu commentsMenu() {
        return new Menu() {
            @Override
            protected void show(){
                options.add("Add new comment");
                options.add("Back");
                System.out.println(options);
            }
            @Override
            public void execute(){
                System.out.println("Enter your choice:");
                do{
                    show();
                    String input;
                    if (!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()) {
                        continue;
                    }
                    switch (input.trim()){
                        case "1":
                            addNewComment();
                        case "2":
                            return;
                    }

                } while(true);
            }

        };
    }

    public void execute(String productId) {
        setProductId(productId);
        System.out.println("Enter Number :");
        String input;
        do {
            show();
            if (!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()) {
                continue;
            }
            switch (input.trim()) {
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
                    commentsMenu().execute();
                    break;
                case "5":
                    show();
                case "6":
                    return;
            }
        } while(true);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}

