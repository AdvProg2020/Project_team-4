package View.Menu;

import Model.Category;
import Model.Product;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class ProductsMenu extends Menu {
    private static ProductsMenu productsMenu = new ProductsMenu();

    public static ProductsMenu getProductsMenu() {
        return productsMenu;
    }

    private ArrayList<Product> products = Product.getAllProducts();

    private ProductsMenu() {
        options.add("sorting");
        options.add("show products");
        options.add("show product");
        options.add("view categories");
        options.add("filter");
        options.add("back");
    }

    private void sorting(){

    }

    private void showCategories() {
        ArrayList<Category> allCategories = Category.getAllCategories();
        for (Category allCategory : allCategories) {
            System.out.println(allCategory.getName());
        }
    }

    private void showProducts(){
        for (Product product : products) {
            System.out.print(product.getName() + "\t");
            System.out.println("product cost:" + product.getCost() +
                    " exist number: " + product.getAmountOfExist() +
                   "productId :" + product.getName());
        }
    }

    private void showProduct(){
        String productId;
        while(true) {
            Matcher matcher = getField("please enter valid productId", "(\\S+)");
            if (matcher == null) {
                return;
            }
            if (checkProductId(matcher.group())) {
                productId = matcher.group();
                break;
            }
        }
        ProductMenu productMenu = new ProductMenu();
        productMenu.execute(productId);
    }

    private static Menu getFilterMenu() {
        return new Menu() {
            @Override
            protected void execute() {
                options.add("show available filters");
                options.add("filter [an available filter]");
                options.add("current filters");
                options.add("disable filter [a selected filter]");
                options.add("end");
                String input = "";
                do {
                    show();
                    System.out.println("Enter Number :");
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch (input.trim()) {
                        case "1":
                            System.out.println("enter filter mode: 1:");
                    }
                }while (true);

            }
        };
    }

    private boolean checkProductId(String productId) {
        return Product.getProductWithBarcode(productId) != null;
    }

    public void execute() {
        String input;
        Matcher matcher;
        do {
            if(!isThisRegexMatch("(\\d)", input = scanner.nextLine())){
                continue;
            }
            switch (input) {
                case "1":
                    sorting();
                    break;
                case "2":
                    showProducts();
                    break;
                case "3":
                    showProduct();
                    break;
                case "4":
                    showCategories();
                case "5":
                    getFilterMenu().execute();
                case "6":
                    return;
            }
        }while(true);
    }

}

