package View.Menu;

import Model.Category;
import Model.Product;

import java.util.*;
import java.util.regex.Matcher;

public class ProductsMenu extends Menu {
    private static ProductsMenu productsMenu = new ProductsMenu();

    private static ProductsMenu getProductsMenu() {
        return productsMenu;
    }
    private static String currentSort = "name (default sort)";
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<String> categoryTags = new ArrayList<>();
    private static ArrayList<String> productTags = new ArrayList<>();

    private ProductsMenu() {
        options.add("sorting");
        options.add("show products");
        options.add("show product");
        options.add("view categories");
        options.add("filter");
        options.add("back");
    }

    private Menu sortingMenu() {
        return new Menu() {
            @Override
            protected void execute() {
                options.add("show available sort");
                options.add("to sort");
                options.add("current sort");
                options.add("disable sort");
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
                            System.out.println("you can sort with time , name, score, seen time");
                            break;
                        case "2":
                            sortingMetod();
                            break;
                        case "3":
                            showCurrentSort();
                            break;
                        case "4":
                            disableSort();
                            break;
                        case "5":
                            return;
                    }
                } while (true);
            }

            private void disableSort() {
                products.sort(Comparator.comparing(Product::getName));
                currentSort = "name (default sort)";
                System.out.println("sorted with defalut sort (name)");
            }

            private void showCurrentSort() {
                System.out.println("sorted with " + currentSort + "!");
            }

            private void sortingMetod() {
                while (true) {
                    System.out.println("for sorting by name press 1  with date 2" +
                            "with score 3 and with seen time 4 for back press 5");
                    String input2 = scanner.nextLine();
                    switch (input2) {
                        case "1":
                            products.sort(Comparator.comparing(Product::getName));
                            System.out.println("sorted");
                            currentSort = "name";
                            return;
                        case "2":
                            products.sort(Comparator.comparing(Product::getLocalDateTime));
                            System.out.println("sorted");
                            currentSort = "date";
                            break;
                        case "3":
                            products.sort(Comparator.comparing(Product::getScoreNo));
                            System.out.println("sorted");
                            currentSort = "score";
                            break;
                        case "4":
                            products.sort(Comparator.comparing(Product::getSeen));
                            System.out.println("sorted");
                            currentSort = "seen";
                            break;
                        case "5":
                            return;
                        default:
                            System.out.println("please enter number 1 to 5");
                            break;
                    }
                }
            }
        };
    }


    private void showCategories() {
        ArrayList<Category> allCategories = Category.getAllCategories();
        for (Category allCategory : allCategories) {
            System.out.println(allCategory.getName());
        }
    }

    private void showProducts() {
        for (Product product : products) {
            System.out.print(product.getName() + "\t");
            System.out.println("product cost:" + product.getCost() +
                    " exist number: " + product.getAmountOfExist() +
                    "productId :" + product.getName());
        }
    }

    private void showProduct() {
        String productId;
        while (true) {
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
                            System.out.println(showAvailableFilters());
                            break;
                        case "2":
                            getTagForFilter();
                            break;
                        case "3":
                            showCurrentFilters();
                            break;
                        case "4":
                            disableFilter();
                            break;
                        case "5":
                            lastFilter();
                            return;
                    }
                } while (true);

            }

            private HashSet showAvailableFilters() {
                HashSet template = new HashSet<String>();
                for (Category allCategory : Category.getAllCategories()) {
                    template.add(allCategory.getName());
                }
                for (Product allProduct : Product.getAllProducts()) {
                    for (String tag : allProduct.getTags()) {
                        template.add(tag);
                    }
                }
                return template;
            }
        };
    }

    private void updateProducts() {
        products = Product.getAllProducts();
    }

    private boolean checkProductId(String productId) {
        return Product.getProductWithBarcode(productId) != null;
    }

    public void execute() {
        String input;
        Matcher matcher;
        do {
            updateProducts();
            if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                continue;
            }
            switch (input) {
                case "1":
                    sortingMenu().execute();
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
        } while (true);
    }

    private static void getTagForFilter() {
        while (true) {
            System.out.println("enter tag or end for back");
            String tag;
            tag = scanner.nextLine();
            if (tag.equals("end")) {
                return;
            }
            boolean found = false;
            for (Category allCategory : Category.getAllCategories()) {
                if (allCategory.getName().equals(tag)) {
                    categoryTags.add(tag);
                    found = true;
                    break;
                }
            }
            if (found) {
                continue;
            }
            for (Product allProduct : Product.getAllProducts()) {
                for (String allProductTag : allProduct.getTags()) {
                    if (allProductTag.equalsIgnoreCase(tag)) {
                        productTags.add(tag);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (found) {
                System.out.println("tag added successfully!");
            } else System.out.println("tag not found");
        }
    }

    private static void showCurrentFilters() {
        System.out.println("category filters:");
        System.out.println(categoryTags);
        System.out.println("tags filters:");
        System.out.println(productTags);
    }

    private static void disableFilter() {
        while (true) {
            System.out.println("please enter your tag or category name and end for back");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) {
                return;
            }
            if (categoryTags.contains(input)) {
                categoryTags.remove(input);
                System.out.println("tag removed successfully");
                continue;
            }
            if (productTags.contains(input)) {
                productTags.remove(input);
                System.out.println("tag removed successfully");
                continue;
            }
            System.out.println("tag not found");
        }
    }

    private static void lastFilter() {
        products.clear();
        for (String categoryTag : categoryTags) {
            products.addAll(Category.getProductsOfACategory(categoryTag, productTags));
        }
        products.addAll(Product.getProductWithTag(productTags));
    }

}

