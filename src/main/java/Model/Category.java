package Model;



import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private static HashMap<String, Category> categories;
    private String name;
    private ArrayList<String> tags;
    private ArrayList<Category> subCategories;
    private ArrayList<Product> products;

    public static ArrayList<Product> searchInCategories() {
        return null;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<Product> getProductsOfACategory(Category category, ArrayList<String> filters) {
        ArrayList<Product> productsToShow = new ArrayList<Product>();
        if (categories.get(category.getName()).products.size() != 0) {
            for (Product product : categories.get(category.getName()).products) {
                if (filters.size() == 0 || product.getCategoryTags().contains(filters)) {
                    productsToShow.add(product);
                }
            }
        }
        for (Category subCategory : categories.get(category.getName()).subCategories) {
            if (subCategory.products != null) {
                subCategory.getProductsOfACategory(subCategory, filters);
            }
        }
        return productsToShow;
    }

    public static String compareBetweenTwoProduct(Category category, Product product1, Product product2) {
        return null;
    }
}
